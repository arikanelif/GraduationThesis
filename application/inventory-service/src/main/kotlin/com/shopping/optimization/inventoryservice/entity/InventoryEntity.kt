package com.shopping.optimization.inventoryservice.entity

import com.shopping.optimization.inventoryservice.model.InventoryResponseModel

data class InventoryEntity (
    val id: Long? = null,
    val skuCode: String? = null,
    val isInStock: Boolean = false
)

