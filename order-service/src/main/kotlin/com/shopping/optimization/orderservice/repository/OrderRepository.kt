package com.shopping.optimization.orderservice.repository

import com.shopping.optimization.orderservice.entity.OrderEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface OrderRepository : ReactiveCrudRepository <OrderEntity, Long > {
}