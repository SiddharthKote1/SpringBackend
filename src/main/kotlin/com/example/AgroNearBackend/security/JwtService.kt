package com.example.AgroNearBackend.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Base64
import java.util.Date

@Service
class JwtService(
    @Value("\${jwt.secret}")
    private val jwtSecret: String
) {
    private val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())

    fun generateToken(email: String): String =
        Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 86400000))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

    fun getEmail(token: String): String =
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body.subject
}
