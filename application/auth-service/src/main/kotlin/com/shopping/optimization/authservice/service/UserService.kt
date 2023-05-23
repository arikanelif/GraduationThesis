package com.shopping.optimization.authservice.service

import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    suspend fun createUser(
        userId: Long,
        email: String,
        firstname: String? = null,
        lastname: String? = null,
        phoneNumber: String? = null,
        password: String,
    ) {
        userRepository.save(
            UserEntity(
                id = userId,
                firstname = firstname,
                lastname = lastname,
                pass = password,
                phoneNumber = phoneNumber,
                email = email,
            ),
        ).awaitSingle()
    }

    suspend fun getUser(userId: Long): UserEntity? {
        return userRepository.findById(userId).awaitSingleOrNull() ?: throw Exception("User not found")
    }
}
