package com.shopping.optimization.orderservice.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import reactor.core.publisher.Mono
import java.math.BigDecimal

class OrderLineItemsRequestModel (
    val price : BigDecimal,
    val quantity : Int,
    val skuCode: String
)

data class CreateBasketRequest(
    val orderLineItems: List<OrderLineItemsRequestModel>
)

data class OrderRequest(
    val customerId: Long,
    val courierId: Long,
    val totalPrice: Double,
    val marketsToProduct: List<Map<String,Any>>,
    val routeId: Long,
    val activeNow: Boolean,
    val basketId: Long,
    val orderId: Long,
)
fun convertStringToOrderModel(
    order: String
): Mono<OrderRequest> {
    val objectMapper = jacksonObjectMapper()
    return objectMapper.readValue(order, objectMapper.typeFactory.constructArrayType(OrderRequest::class.java))
}