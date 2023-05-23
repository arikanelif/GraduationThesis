package com.shopping.optimization.authservice.controller

import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/{userId}")
    suspend fun createUser(
        @PathVariable userId: Long,
        @RequestParam email: String,
        @RequestParam firstname: String?,
        @RequestParam lastname: String?,
        @RequestParam password: String,
        @RequestParam phoneNumber: String?,
    ) {
        userService.createUser(
            userId = userId,
            email = email,
            firstname = firstname,
            lastname = lastname,
            password = password,
            phoneNumber = phoneNumber,
        )
    }

    @GetMapping("/{userId}")
    suspend fun getUser(
        @PathVariable userId: Long,
    ): UserEntity? {
        return userService.getUser(userId)
    }
}
