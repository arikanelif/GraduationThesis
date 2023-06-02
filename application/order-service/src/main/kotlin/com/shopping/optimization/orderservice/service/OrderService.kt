package com.shopping.optimization.orderservice.service

import com.shopping.optimization.orderservice.config.WebClientConfig
import com.shopping.optimization.orderservice.entity.OrderEntity
import com.shopping.optimization.orderservice.model.InventoryResponseModel
import com.shopping.optimization.orderservice.model.OrderLineItemsRequestModel
import com.shopping.optimization.orderservice.model.convertStringToOrderModel
import com.shopping.optimization.orderservice.repository.OrderRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val webClient: WebClientConfig
    ){
    suspend fun placeOrder(
        customerID: Long,
        courierID: Long,
        orderLineItems: List<OrderLineItemsRequestModel>
    ){
        val skuCodes = orderLineItems.map { it.skuCode }

        val inventoryResponse: Mono<List<InventoryResponseModel>> =
            webClient.webClient()
            .baseUrl("http://localhost:8084").build()
                .get()
                .uri { uriBuilder ->
                    uriBuilder
                        .path("/api/inventory")
                        .queryParam("skuCode", skuCodes).build()
                    }
                .retrieve()
                .bodyToMono(List::class.java).flatMap { response ->
                    Mono.just(response.map { it as InventoryResponseModel })
                }

        inventoryResponse.flatMap { response ->
            val allInStock = response.all(InventoryResponseModel::isInStock)
            if (allInStock) {
                OrderEntity(
                    customerId = customerID,
                    courierId = courierID,
                    orderLineItems = orderLineItems
                ).let { orderRepository.save(it) }
            } else {
                Mono.error(Exception("Product is not in stock!"))
            }
        }.awaitSingle()
    }

    suspend fun createOrder(){
        val result = webClient.webClient().build()
            .get()
            .uri("https://gradproject.herokuapp.com/createOrderFromBasketId/1")
            .retrieve()
            .bodyToMono(String::class.java).awaitSingle()

        val orderModel = convertStringToOrderModel(result).awaitSingle()

    }

}