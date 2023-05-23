package com.shopping.optimization.orderservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.shopping.optimization.orderservice.entity.BasketEntity
import com.shopping.optimization.orderservice.entity.convertListOfOrderLineItemsModelToString
import com.shopping.optimization.orderservice.entity.convertStringToListOfOrderLineItemsModel
import com.shopping.optimization.orderservice.model.OrderLineItemsRequestModel
import com.shopping.optimization.orderservice.repository.BasketRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class BasketService(
    private val basketRepository: BasketRepository
) {
    suspend fun createBasket(
        orderLineItems: List<OrderLineItemsRequestModel>
    ){
        val basket = BasketEntity(
            orderLineItems = convertListOfOrderLineItemsModelToString(orderLineItems),
            customerId = 1
        )
        basketRepository.save(basket).awaitSingle()
    }

    suspend fun getCustomerBasket(
        customerId: Long
    ): List<OrderLineItemsRequestModel> {
        val basket = basketRepository.findByCustomerId(customerId).awaitSingle()
        return convertStringToListOfOrderLineItemsModel(basket.orderLineItems!!)
    }

    suspend fun deleteCustomerBasket(
        customerId: Long
    ) {
        basketRepository.deleteByCustomerId(customerId).awaitSingle()
    }
}