package com.shopping.optimization.authservice.entity

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.shopping.optimization.authservice.model.Role
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Table("user")
data class UserEntity(
    val id: Long? = null,
    val firstname: String,
    val lastname: String,
    val pass: String,
    val email: String,
    val phoneNumber: String,
    val addressId: Long? = null,
    @JsonEnumDefaultValue
    val role: Role,
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
