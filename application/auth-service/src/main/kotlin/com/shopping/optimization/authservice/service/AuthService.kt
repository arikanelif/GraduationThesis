package com.shopping.optimization.authservice.service

import com.shopping.optimization.authservice.config.JwtService
import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.model.AuthenticationRequest
import com.shopping.optimization.authservice.model.AuthenticationResponse
import com.shopping.optimization.authservice.model.RegisterRequest
import com.shopping.optimization.authservice.model.Role
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
) {

    suspend fun register(request: RegisterRequest): AuthenticationResponse {
        val user = UserEntity(
            firstname = request.firstname!!,
            lastname = request.lastname!!,
            email = request.email!!,
            pass = passwordEncoder.encode(request.password),
            role = Role.USER,
            phoneNumber = request.phoneNumber!!,
        )
        val savedUser = userRepository.save(user).awaitSingle()
        val jwtToken = jwtService.generateToken(savedUser)
        val refreshToken = jwtService.generateRefreshToken(savedUser)
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
        return AuthenticationResponse(jwtToken, refreshToken)
    }
}
