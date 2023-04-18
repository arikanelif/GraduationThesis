package com.shopping.optimization.marketservice.service

import com.shopping.optimization.marketservice.model.MarketResponse
import com.shopping.optimization.marketservice.repository.MarketRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux

@Service
class MarketService(
    private val marketRepository: MarketRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional(readOnly = true)

    fun isInStock(skuCode: List<String>): Flux<MarketResponse> {
        logger.info("Checking Inventory")
        return marketRepository.findBySkuCode(skuCode)
            .map { inventory ->
                MarketResponse(
                    skuCode = inventory.skuCode,
                    isInStock = inventory.quantity > 0
                )
            }
    }
}