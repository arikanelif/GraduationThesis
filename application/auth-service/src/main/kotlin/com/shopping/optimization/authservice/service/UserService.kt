package com.shopping.optimization.authservice.service

import com.shopping.optimization.authservice.entity.UserEntity
import com.shopping.optimization.authservice.model.Role
import com.shopping.optimization.authservice.model.UserType
import com.shopping.optimization.authservice.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    suspend fun createUser(
        userId: Long,
        type: UserType,
        email: String,
        firstname: String,
        lastname: String,
        phoneNumber: String,
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
                role = Role.USER,
            ),
        ).awaitSingle()
    }

    suspend fun getUser(userId: Long): UserEntity {
        return userRepository.findById(userId).awaitSingle()
    }
}
