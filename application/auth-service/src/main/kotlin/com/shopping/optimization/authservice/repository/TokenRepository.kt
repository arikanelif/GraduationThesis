package com.shopping.optimization.authservice.repository

import com.shopping.optimization.authservice.entity.TokenEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TokenRepository : ReactiveCrudRepository<TokenEntity, Long> {

    fun findByToken(token: String): Mono<TokenEntity>

    @Query("SELECT * FROM token WHERE user_id = :id AND revoked = false AND expired = false")
    fun findAllValidTokenByUser(id: Long): Flux<TokenEntity>
}
