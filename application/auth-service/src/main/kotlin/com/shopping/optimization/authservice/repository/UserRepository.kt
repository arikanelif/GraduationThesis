package com.shopping.optimization.authservice.repository

import com.shopping.optimization.authservice.entity.UserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface UserRepository : ReactiveCrudRepository<UserEntity, Long> {
    fun findByEmail(email: String): Mono<UserEntity>
}
