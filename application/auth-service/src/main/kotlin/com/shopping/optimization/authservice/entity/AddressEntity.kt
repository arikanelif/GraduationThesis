package com.shopping.optimization.authservice.entity
import org.springframework.data.relational.core.mapping.Table

@Table("address")
data class AddressEntity(
    val id: Long? = null,
    val street: String,
    val city: String,
    val state: String,
    val openAddress: String,
    val country: String,
)
