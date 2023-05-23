package com.shopping.optimization.authservice.service

import com.shopping.optimization.authservice.entity.TokenEntity
import com.shopping.optimization.authservice.repository.TokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Service

@Service
class LogoutService(
    private val tokenRepository: TokenRepository,
) : LogoutHandler {

    suspend fun setTokenExpired(token: TokenEntity): TokenEntity {
        return token.copy(
            expired = true,
            revoked = true,
        ).let(tokenRepository::save).awaitSingle()
    }
    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse?,
        authentication: Authentication?,
    ) {
        val authHeader = request.getHeader("Authorization")
        if (!authHeader.startsWith("Bearer ")) {
            return
        }
        val jwt = authHeader.substring(7)
        val storedToken = tokenRepository.findByToken(jwt).block()
        if (storedToken != null) {
            runBlocking { setTokenExpired(storedToken) }
            SecurityContextHolder.clearContext()
        }
    }
}
