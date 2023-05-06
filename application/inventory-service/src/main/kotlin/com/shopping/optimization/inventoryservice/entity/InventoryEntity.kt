package com.shopping.optimization.inventoryservice.entity

data class InventoryEntity (
    val id: Long? = null,
    val skuCode: String? = null,
    val isInStock: Boolean = false
)
