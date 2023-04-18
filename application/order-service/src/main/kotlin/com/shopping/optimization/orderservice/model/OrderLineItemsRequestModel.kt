package com.shopping.optimization.orderservice.model

import java.math.BigDecimal

class OrderLineItemsRequestModel (
    val price : BigDecimal,
    val quantity : Int,
    val skuCode: String
)