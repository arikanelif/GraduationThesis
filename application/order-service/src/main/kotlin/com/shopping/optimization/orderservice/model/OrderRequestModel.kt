package com.shopping.optimization.orderservice.model


class OrderRequestModel (
    val customerId: Long,
    val courierId: Long,
    val orderLineItems: List<OrderLineItemsRequestModel>)