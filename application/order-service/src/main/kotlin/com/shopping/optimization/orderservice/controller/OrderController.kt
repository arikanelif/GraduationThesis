package com.shopping.optimization.orderservice.controller

import com.shopping.optimization.orderservice.model.OrderRequestModel
import com.shopping.optimization.orderservice.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/order")
class OrderController (
    private val orderService: OrderService,
    ){
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun placeOrder(
        @RequestBody orderRequest: OrderRequestModel
    ) {
        orderService.placeOrder(
            customerID = orderRequest.customerId,
            courierID = orderRequest.courierId,
            orderLineItems = orderRequest.orderLineItems
        )
        logger.info("Order placed successfully")
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun createOrder(

    ){
        orderService.createOrder()
    }

}