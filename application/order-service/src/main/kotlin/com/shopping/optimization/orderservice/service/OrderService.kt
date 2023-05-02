package com.shopping.optimization.orderservice.service

import com.shopping.optimization.orderservice.entity.OrderEntity
import com.shopping.optimization.orderservice.entity.OrderLineEntity
import com.shopping.optimization.orderservice.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService (
    private val orderRepository: OrderRepository
    ){
    fun placeOrder(
        customerID: Long,
        courierID: Long,
        orderLineItems: List<OrderLineEntity>
    ){
        OrderEntity(
            customerId = customerID,
            courierId = courierID,
            orderLineItems = orderLineItems
        ).let { orderRepository.save(it) }

    }
}