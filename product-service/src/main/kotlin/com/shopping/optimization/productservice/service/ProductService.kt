package com.shopping.optimization.productservice.service

import com.shopping.optimization.productservice.entity.ProductEntity
import com.shopping.optimization.productservice.model.ProductRequest
import com.shopping.optimization.productservice.repository.ProductRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    suspend fun createProduct(
        productRequest: ProductRequest
    ): ProductEntity {
        return ProductEntity(
            description = productRequest.description,
            brand = productRequest.brand
        ).let(productRepository::save).awaitSingle()
    }
}
