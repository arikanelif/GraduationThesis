package com.shopping.optimization.authservice.model

class RegisterRequest(
    val id : Long? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val email: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val token: String? = null
)
