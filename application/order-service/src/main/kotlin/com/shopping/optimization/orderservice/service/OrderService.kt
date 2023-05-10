package com.shopping.optimization.orderservice.service

import com.shopping.optimization.orderservice.config.WebClientConfig
import com.shopping.optimization.orderservice.entity.OrderEntity
import com.shopping.optimization.orderservice.model.InventoryResponseModel
import com.shopping.optimization.orderservice.model.OrderLineItemsRequestModel
import com.shopping.optimization.orderservice.model.orderLineItemsRequestModelToOrderEntity
import com.shopping.optimization.orderservice.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val webClient: WebClientConfig
    ){
    fun placeOrder(
        customerID: Long,
        courierID: Long,
        orderLineItems: List<OrderLineItemsRequestModel>
    ){
        val listInventory :List<InventoryResponseModel> = listOf()

        val skuCodes = orderLineItems.map { it.skuCode }

        val inventoryResponse : List<InventoryResponseModel>? = webClient.webClient().build().get()
            .uri("http://localhost:8084/api/inventory/test")
            //{ uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build() }
            .retrieve()
            .bodyToMono(listInventory::class.java).block()

        val allInStock = inventoryResponse?.stream()?.allMatch(InventoryResponseModel::isInStock)
        if (allInStock == true){
            OrderEntity(
                customerId = customerID,
                courierId = courierID,
                orderLineItems = orderLineItemsRequestModelToOrderEntity(orderLineItems)
            ).let { orderRepository.save(it) }
        }else{
            throw Exception("Product is not in stock!")
        }


    }

}