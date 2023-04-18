package com.shopping.optimization.authservice.controller

import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.model.UserType
import com.shopping.optimization.authservice.service.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/{userId}")
    @Operation(summary = "Create a new user")
    suspend fun createUser(
        @PathVariable userId: Long,
        @PathVariable type: UserType,
        @PathVariable email: String,
        @PathVariable username: String,
        @PathVariable password: String,
    ) {
        userService.createUser(userId, type, email, username, password)
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get a user by id")
    suspend fun getUser(
        @PathVariable userId: Long,
    ): UserEntity {
        return userService.getUser(userId)
    }
}