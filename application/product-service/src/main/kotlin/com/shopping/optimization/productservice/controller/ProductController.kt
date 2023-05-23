package com.shopping.optimization.productservice.controller

import com.shopping.optimization.productservice.entity.ProductEntity
import com.shopping.optimization.productservice.model.ProductRequest
import com.shopping.optimization.productservice.repository.ProductRepository
import com.shopping.optimization.productservice.service.ProductService
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/product")
class ProductController(
    private val productService: ProductService,
    private val productRepository: ProductRepository
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun createProduct(
        @RequestBody productRequest: ProductRequest
    ) {
        productService.createProduct(productRequest)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun getAllProducts(): List<ProductEntity> {
        return productRepository.findAll().collectList().awaitSingle()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    suspend fun getProductById(
        @PathVariable id: Long
    ): ProductEntity? {
        return productRepository.findById(id).awaitSingleOrNull() ?: throw Exception("Product with id $id not found")
    }
}
