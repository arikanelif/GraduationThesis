package com.shopping.optimization.authservice.service

import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.model.UserType
import com.shopping.optimization.authservice.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    suspend fun createUser(
        userId: Long,
        type: UserType,
        email: String,
        username: String,
        password: String,
    ) {
        userRepository.save(UserEntity(
            id = userId,
            email = email,
            username = username,
            password = password,
        )).awaitSingle()
    }

    suspend fun getUser(userId: Long): UserEntity {
        return userRepository.findById(userId).awaitSingle()
    }

}