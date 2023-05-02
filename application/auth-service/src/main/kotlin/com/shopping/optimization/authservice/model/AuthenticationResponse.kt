package com.shopping.optimization.authservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticationResponse(
    @JsonProperty("access_token")
    private val accessToken: String? = null,

    @JsonProperty("refresh_token")
    private val refreshToken: String? = null,
)
