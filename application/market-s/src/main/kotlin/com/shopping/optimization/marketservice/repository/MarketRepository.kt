package com.shopping.optimization.marketservice.repository

import com.shopping.optimization.marketservice.entity.MarketEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface MarketRepository: ReactiveCrudRepository<MarketEntity, Long> {

    fun findBySkuCode(skuCode: List<String>): Flux<MarketEntity>
}