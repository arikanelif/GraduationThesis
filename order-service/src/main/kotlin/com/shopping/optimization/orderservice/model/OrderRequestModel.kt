package com.shopping.optimization.orderservice.model

import com.shopping.optimization.orderservice.entity.OrderLineEntity

class OrderRequestModel (
    val orderLineItems: List<OrderLineItemsRequestModel>)