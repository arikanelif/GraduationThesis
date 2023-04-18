package com.shopping.optimization.authservice.service

import com.shopping.optimization.authservice.config.JwtService
import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.model.AuthenticationRequest
import com.shopping.optimization.authservice.model.AuthenticationResponse
import com.shopping.optimization.authservice.model.RegisterRequest
import com.shopping.optimization.authservice.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.management.relation.Role


@Service
class AuthService(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {


    suspend fun register(request: RegisterRequest): AuthenticationResponse {
        val user = UserEntity(
            firstname = request.firstname!!,
            lastname = request.lastname!!,
            email = request.email!!,
            pass = passwordEncoder!!.encode(request.password)

        )
        val savedUser = repository.save(user).awaitSingle()
        val jwtToken = jwtService!!.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)
        saveUserToken(savedUser, jwtToken)
        return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build()
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )
        val user: Unit = repository!!.findByEmail(request.email!!)
            .orElseThrow()
        val jwtToken = jwtService!!.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)
        revokeAllUserTokens(user)
        saveUserToken(user, jwtToken)
        return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build()
    }

    private fun saveUserToken(user: User, jwtToken: String?) {
        val token: Unit = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build()
        tokenRepository.save(token)
    }

    private fun revokeAllUserTokens(user: User) {
        val validUserTokens: Unit = tokenRepository.findAllValidTokenByUser(user.getId())
        if (validUserTokens.isEmpty()) return
        validUserTokens.forEach { token ->
            token.setExpired(true)
            token.setRevoked(true)
        }
        tokenRepository.saveAll(validUserTokens)
    }

    @Throws(IOException::class)
    fun refreshToken(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        val refreshToken: String
        val userEmail: String?
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return
        }
        refreshToken = authHeader.substring(7)
        userEmail = jwtService!!.extractUsername(refreshToken)
        if (userEmail != null) {
            val user: Unit = repository!!.findByEmail(userEmail)
                .orElseThrow()
            if (jwtService.isTokenValid(refreshToken, user)) {
                val accessToken = jwtService.generateToken(user)
                revokeAllUserTokens(user)
                saveUserToken(user, accessToken)
                val authResponse: Unit = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build()
                ObjectMapper().writeValue(response.outputStream, authResponse)
            }
        }
    }
}