package com.shopping.optimization.authservice.entity

import com.shopping.optimization.authservice.model.Role
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Table("user_")
data class UserEntity(
    val id: Long? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    @Column("password")
    val pass: String,
    val email: String,
    val phoneNumber: String? = null,
    val addressId: Long? = null,
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf(SimpleGrantedAuthority(Role.USER.name))
    }

    override fun getPassword(): String {
        return pass
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
