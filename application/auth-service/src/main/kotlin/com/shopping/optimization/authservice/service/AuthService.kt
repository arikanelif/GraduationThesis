package com.shopping.optimization.authservice.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.shopping.optimization.authservice.config.JwtService
import com.shopping.optimization.authservice.entity.TokenEntity
import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.model.AuthenticationRequest
import com.shopping.optimization.authservice.model.AuthenticationResponse
import com.shopping.optimization.authservice.model.RegisterRequest
import com.shopping.optimization.authservice.repository.TokenRepository
import com.shopping.optimization.authservice.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
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

    fun convert(
        request: String
    ): RegisterRequest{
        val objectMapper = jacksonObjectMapper()
        return objectMapper.readValue(request, RegisterRequest::class.java)
    }
    suspend fun register(registerRequest: String): AuthenticationResponse {
        val request = convert(registerRequest)
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
        //saveUserToken(savedUser, jwtToken)
        return AuthenticationResponse(jwtToken)
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
        //revokeAllUserTokens(user)
        //saveUserToken(user, jwtToken)
        return AuthenticationResponse(jwtToken)
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
}
