package com.shopping.optimization.marketservice.controller

import com.shopping.optimization.marketservice.entity.MarketEntity
import com.shopping.optimization.marketservice.model.MarketResponse
import com.shopping.optimization.marketservice.repository.MarketRepository
import com.shopping.optimization.marketservice.service.MarketService
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/market")
class MarketController(
    private val marketRepository: MarketRepository,
    private val marketService: MarketService,
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun isInStock(
        @RequestParam skuCode: List<String>
    ): List<MarketResponse>? {
        logger.info("Received inventory check request for skuCode: {}", skuCode);
        return marketService.isInStock(skuCode)
    }


}