package com.shopping.optimization.orderservice.model

import com.shopping.optimization.orderservice.entity.OrderLineEntity

class OrderRequestModel (
    val customerId: Long,
    val courierId: Long,
    val orderLineItems: List<OrderLineItemsRequestModel>)