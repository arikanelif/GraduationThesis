package com.shopping.optimization.orderservice

import com.shopping.optimization.orderservice.entity.BasketEntity
import com.shopping.optimization.orderservice.model.OrderLineItemsRequestModel
import com.shopping.optimization.orderservice.repository.BasketRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "100000")
@ActiveProfiles("test")
class OrderServiceApplicationTests(
	@Autowired private val basketRepository: BasketRepository
) {
	@Test
	fun `create basket`(){
		val orderLineItemsRequestModel = listOf(
			OrderLineItemsRequestModel(
				price = BigDecimal(10),
				quantity = 1,
				skuCode = "peynir"
			),
			OrderLineItemsRequestModel(
				price = BigDecimal(2),
				quantity = 1,
				skuCode = "su")
		)
		val basket = BasketEntity(
			customerId = 1,
			orderLineItems = orderLineItemsRequestModel
		)
		val id = basketRepository.save(basket).block()?.customerId

		assertEquals(1, id)

	}

}
