package com.shopping.optimization.orderservice.repository

import com.shopping.optimization.orderservice.entity.BasketEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface BasketRepository: ReactiveCrudRepository<BasketEntity, Long>{

    fun findByCustomerId(customerId: Long): Mono<BasketEntity>

    fun deleteByCustomerId(customerId: Long): Mono<Void>
}
