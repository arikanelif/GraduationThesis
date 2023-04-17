package com.shopping.optimization.authservice.entity

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.shopping.optimization.authservice.model.UserType
import org.springframework.data.relational.core.mapping.Table

@Table("user")
data class UserEntity(
    val id: Long,
    val username: String,
    val password: String,
    val email: String,
    val type: UserType,
)