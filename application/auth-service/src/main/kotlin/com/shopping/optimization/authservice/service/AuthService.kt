package com.shopping.optimization.authservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.shopping.optimization.authservice.config.JwtService
import com.shopping.optimization.authservice.entity.TokenEntity
import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.model.AuthenticationRequest
import com.shopping.optimization.authservice.model.AuthenticationResponse
import com.shopping.optimization.authservice.model.RegisterRequest
import com.shopping.optimization.authservice.repository.TokenRepository
import com.shopping.optimization.authservice.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val tokenRepository: TokenRepository,
    private val logoutService: LogoutService,
) {

    suspend fun register(request: RegisterRequest): AuthenticationResponse {
        val user = UserEntity(
            firstname = request.firstname!!,
            lastname = request.lastname!!,
            email = request.email!!,
            pass = passwordEncoder.encode(request.password),
            phoneNumber = request.phoneNumber!!,
        )
        userRepository.save(user).awaitSingle()
        val savedUser = userRepository.findByEmail(request.email).awaitSingle()
        val jwtToken = jwtService.generateToken(savedUser)
        val refreshToken = jwtService.generateRefreshToken(savedUser)
        saveUserToken(savedUser, jwtToken)
        return AuthenticationResponse(jwtToken, refreshToken)
    }

    suspend fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password,
            ),
        )
        val user = userRepository.findByEmail(request.email!!).awaitSingleOrNull() ?: throw Exception("User not found")
        val jwtToken = jwtService.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)
        revokeAllUserTokens(user)
        saveUserToken(user, jwtToken)
        return AuthenticationResponse(jwtToken, refreshToken)
    }

    suspend fun saveUserToken(user: UserEntity, jwtToken: String?) {
        TokenEntity(
            token = jwtToken,
            userId = user.id,
            expired = false,
            revoked = false,
            tokenType = "Bearer",
        ).let { tokenRepository.save(it) }.awaitSingle()
    }

    suspend fun revokeAllUserTokens(user: UserEntity) {
        val list = tokenRepository.findAllValidTokenByUser(user.id!!).collectList().awaitSingle()
        list.forEach {
            logoutService.setTokenExpired(it)
        }
    }

    suspend fun refreshToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        val userEmail: String?
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return
        }
        val refreshToken: String = authHeader.substring(7)
        userEmail = jwtService.extractUsername(refreshToken)
        if (userEmail != null) {
            val user = userRepository.findByEmail(userEmail).awaitSingleOrNull() ?: throw Exception("User not found")
            if (jwtService.isTokenValid(refreshToken, user)) {
                val accessToken = jwtService.generateToken(user)
                revokeAllUserTokens(user)
                saveUserToken(user, accessToken)
                AuthenticationResponse(
                    accessToken = accessToken,
                    refreshToken = refreshToken,
                ).let { ObjectMapper().writeValue(response.outputStream, it) }
            }
        }
    }
}
