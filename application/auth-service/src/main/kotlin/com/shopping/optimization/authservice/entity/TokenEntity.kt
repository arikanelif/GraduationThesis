package com.shopping.optimization.authservice.entity

import org.springframework.data.relational.core.mapping.Table

@Table("token")
data class TokenEntity(
    val id: Int? = null,
    val token: String? = null,
    val tokenType: String? = "BEARER",
    val revoked: Boolean = false,
    val expired: Boolean = false,
    val userId: Long? = null,
)
