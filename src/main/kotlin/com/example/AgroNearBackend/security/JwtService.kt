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

    private val secretKey = Keys.hmacShaKeyFor(
        Base64.getDecoder().decode(jwtSecret)
    )

    private val accessTokenValidityMs = 15 * 60 * 1000L

    fun generateAccessToken(userEmail: String): String {
        val now = Date()
        val expiryDate = Date(now.time + accessTokenValidityMs)

        return Jwts.builder()
            .setSubject(userEmail)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return parseAllClaims(token) != null
    }

    fun getEmailFromToken(token: String): String {
        val claims = parseAllClaims(token)
            ?: throw RuntimeException("Invalid token")
        return claims.subject
    }

    private fun parseAllClaims(token: String): Claims? {
        val rawToken = if (token.startsWith("Bearer ")) {
            token.removePrefix("Bearer ")
        } else token

        return try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(rawToken)
                .body
        } catch (e: Exception) {
            null
        }
    }
}
