package com.shopping.optimization.productservice.model

class ProductRequest(
    val description: String? = null,
    val brand: String
)

class ProductResponse(
    val id: Long,
    val description: String? = null,
    val brand: String
)
