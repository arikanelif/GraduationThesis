package com.shopping.optimization.productservice

import com.shopping.optimization.productservice.entity.ProductEntity
import com.shopping.optimization.productservice.model.ProductRequest
import com.shopping.optimization.productservice.model.ProductResponse
import com.shopping.optimization.productservice.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
class ProductServiceApplicationTests(
    @Autowired webTestClient: WebTestClient,
    @Autowired private val productRepository: ProductRepository
){

    private val webClient = webTestClient.mutate().defaultHeaders {
        it.setBearerAuth("two-factor-enabled-user")
    }.build()
    @BeforeEach
    fun cleanDatabase() {
        productRepository.deleteAll().block()
    }

    @Test
    fun `should create product`(){
        val productId = ProductRequest(
            description = "ekmek",
            brand = "uno"
        ).let{ wsCreateProduct(it) }?.id!!

        val product = productRepository.findById(productId).block()

        assertThat(product).isNotNull
        assertThat(product!!.id).isEqualTo(productId)

    }

    private fun wsCreateProduct(
        model: ProductRequest,
        status: HttpStatus = HttpStatus.OK
    ): ProductResponse? {
        val response = webClient
            .post()
            .uri("/api/product")
            .bodyValue(model)
            .exchange()
            .expectStatus().isEqualTo(status)

        if (!status.is2xxSuccessful) return null

        return response
            .expectBody(ProductResponse::class.java)
            .returnResult()
            .responseBody!!
    }

}
