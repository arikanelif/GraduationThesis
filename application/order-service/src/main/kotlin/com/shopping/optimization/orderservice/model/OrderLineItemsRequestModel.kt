package com.shopping.optimization.orderservice.model

import com.shopping.optimization.orderservice.entity.OrderLineEntity
import java.math.BigDecimal

class OrderLineItemsRequestModel (
    val price : BigDecimal,
    val quantity : Int,
    val skuCode: String
)

fun orderLineItemsRequestModelToOrderEntity(
    orderLineItemsRequestModel: List<OrderLineItemsRequestModel>
) = List(
    orderLineItemsRequestModel.size
) { index ->
    OrderLineEntity(
        price = orderLineItemsRequestModel[index].price,
        quantity = orderLineItemsRequestModel[index].quantity,
        skuCode = orderLineItemsRequestModel[index].skuCode
    )
}
