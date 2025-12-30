package com.example.AgroNearBackend.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date

@Service
class JwtService(

    @Value("\${jwt.secret}")
    private val jwtSecret: String

) {

    // HS256 needs at least 32 bytes
    private val secretKey = Keys.hmacShaKeyFor(
        jwtSecret.toByteArray()
    )

    private val accessTokenValidityMs = 15 * 60 * 1000L // 15 minutes

    fun generateAccessToken(email: String): String {
        val now = Date()
        val expiryDate = Date(now.time + accessTokenValidityMs)

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            parseAllClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getEmailFromToken(token: String): String {
        return parseAllClaims(token).subject
    }

    private fun parseAllClaims(token: String): Claims {
        val rawToken = if (token.startsWith("Bearer ")) {
            token.removePrefix("Bearer ")
        } else token

        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(rawToken)
            .body
    }
}
