package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.dto.LoginRequest
import com.example.AgroNearBackend.dto.RegisterRequest
import com.example.AgroNearBackend.repository.UserRepository
import com.example.AgroNearBackend.security.JwtService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val repo: UserRepository,
    private val encoder: PasswordEncoder,
    private val jwt: JwtService
) {

    fun register(req: RegisterRequest) {
        if (repo.existsByEmail(req.email)) throw RuntimeException("Email exists")

        repo.save(
            User(
                name = req.name,
                email = req.email,
                password = encoder.encode(req.password)
            )
        )
    }

    fun login(req: LoginRequest): String {
        val user = repo.findByEmail(req.email)
            ?: throw RuntimeException("User not found")

        if (!encoder.matches(req.password, user.password))
            throw RuntimeException("Invalid credentials")

        return jwt.generateToken(user.email)
    }
}
