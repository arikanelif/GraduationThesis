package com.shopping.optimization.authservice.controller

import com.shopping.optimization.authservice.model.AuthenticationRequest
import com.shopping.optimization.authservice.model.AuthenticationResponse
import com.shopping.optimization.authservice.model.RegisterRequest
import com.shopping.optimization.authservice.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/register")
    suspend fun register(
        @RequestBody request: RegisterRequest,
    ): ResponseEntity<AuthenticationResponse?>? {
        return ResponseEntity.ok(authService.register(request))
    }

    @PostMapping("/authenticate")
    suspend fun authenticate(
        @RequestBody request: AuthenticationRequest,
    ): ResponseEntity<AuthenticationResponse?> {
        return ResponseEntity.ok(authService.authenticate(request))
    }
}
