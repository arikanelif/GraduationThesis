package com.shopping.optimization.orderservice.entity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.shopping.optimization.orderservice.model.OrderLineItemsRequestModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("basket_")
data class BasketEntity (
    @Id val id: Long? = null,
    @Column("customer_id") val customerId: Long,
    @Column("order_line_items") val orderLineItems: String? = null
)

// convert a list of OrderLineItemsRequestModel to a json format string

fun convertListOfOrderLineItemsModelToString(
    orderLineItemsRequestModel: List<OrderLineItemsRequestModel>
): String {
    val objectMapper = ObjectMapper()
    return objectMapper.writeValueAsString(orderLineItemsRequestModel)
}
fun convertStringToListOfOrderLineItemsModel(
    orderLineItemsString: String
): List<OrderLineItemsRequestModel> {
    val objectMapper = jacksonObjectMapper()
    return objectMapper.readValue(orderLineItemsString, objectMapper.typeFactory.constructCollectionType(List::class.java, OrderLineItemsRequestModel::class.java))
}