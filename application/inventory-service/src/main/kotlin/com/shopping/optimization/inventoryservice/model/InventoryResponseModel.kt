package com.shopping.optimization.inventoryservice.model

import com.fasterxml.jackson.annotation.JsonProperty

class InventoryResponseModel (
    @JsonProperty("skuCode")
    val skuCode: String? = null,
    @JsonProperty("isInStock")
    val isInStock: Boolean = false
)