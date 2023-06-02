package com.shopping.optimization.orderservice.entity

import com.shopping.optimization.orderservice.model.OrderLineItemsRequestModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table("order")
data class OrderEntity (
    @Id
    val id: Long? = null,

    val customerId: Long,
    val courierId: Long? = null,
    val complete: Boolean = false,

    @Column("order_line_items")
    val orderLineItems: List<OrderLineItemsRequestModel>

    )