package com.shopping.optimization.orderservice.controller

import com.shopping.optimization.orderservice.model.OrderRequestModel
import com.shopping.optimization.orderservice.repository.OrderRepository
import com.shopping.optimization.orderservice.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import kotlin.math.log


@RestController
@RequestMapping("api/order")
class OrderController (
    private val orderService: OrderService,
    ){
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun placeOrder(
        @RequestBody orderRequest: OrderRequestModel
    ) {
        orderService.placeOrder(
            customerID = orderRequest.customerId,
            courierID = orderRequest.courierId,
            orderLineItems = orderRequest.orderLineItems
        )
        logger.info("Order placed successfully")
    }

    @GetMapping
    fun getAllOrders() {

    }
}