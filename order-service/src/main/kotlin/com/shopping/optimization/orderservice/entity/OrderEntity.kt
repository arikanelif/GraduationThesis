package com.shopping.optimization.orderservice.entity

import org.springframework.data.relational.core.mapping.Table


@Table("order")
data class OrderEntity (
    val id: Long? = null,
    val customerId: Long,
    val courierId: Long? = null,
    val complete: Boolean = false,
    val orderLineItems: List<OrderLineEntity>
    )