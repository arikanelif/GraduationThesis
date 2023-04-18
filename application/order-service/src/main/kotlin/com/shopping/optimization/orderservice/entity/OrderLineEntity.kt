package com.shopping.optimization.orderservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal


@Table("order_line_items")
data class OrderLineEntity (
    @Id
    val id: Long? = null,
    val price : BigDecimal,
    val quantity : Int,
    val skuCode: String
    )