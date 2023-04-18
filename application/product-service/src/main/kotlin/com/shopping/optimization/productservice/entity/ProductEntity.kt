package com.shopping.optimization.productservice.entity

import org.springframework.data.relational.core.mapping.Table

@Table("product")
data class ProductEntity(
    val id: Long? = null,
    val brand: String,
    val description: String? = null
)
