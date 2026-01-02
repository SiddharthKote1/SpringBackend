package com.example.AgroNearBackend.security

import com.example.AgroNearBackend.repository.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = req.getHeader("Authorization")

        if (header?.startsWith("Bearer ") == true) {
            val token = header.removePrefix("Bearer ")
            val email = jwtService.getEmail(token)
            val user = userRepository.findByEmail(email)

            if (user != null) {
                val auth = UsernamePasswordAuthenticationToken(user, null, emptyList())
                SecurityContextHolder.getContext().authentication = auth
            }
        }

        chain.doFilter(req, res)
    }
}

