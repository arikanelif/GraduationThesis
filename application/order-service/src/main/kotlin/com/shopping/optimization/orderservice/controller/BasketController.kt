package com.shopping.optimization.orderservice.controller

import com.shopping.optimization.orderservice.model.CreateBasketRequest
import com.shopping.optimization.orderservice.model.OrderLineItemsRequestModel
import com.shopping.optimization.orderservice.service.BasketService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/basket")
class BasketController(
    private val basketService: BasketService,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun createBasket(
        @RequestBody request: CreateBasketRequest
    ) {
        basketService.createBasket(request.orderLineItems)
        logger.info("Basket created successfully")
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun getCustomerBasket(
        @RequestParam("customerId") customerId: Long
    ): List<OrderLineItemsRequestModel> {
        return basketService.getCustomerBasket(customerId)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun deleteCustomerBasket(
        @RequestParam("customerId") customerId: Long
    ) {
        basketService.deleteCustomerBasket(customerId)
    }

}