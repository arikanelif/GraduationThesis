package com.shopping.optimization.productservice.entity

import org.springframework.data.relational.core.mapping.Table

@Table("markets_product")
data class MarketProductEntity (
    val id: Long? = null,
    val marketId: Long,
    val productId: Long,
    val price: Double,
    val stock: Int = 0
)