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
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    fun register(request: RegisterRequest) {

        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

        val user = User(
            name = request.name,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            role = "USER"
        )

        userRepository.save(user)
    }

    fun login(request: LoginRequest): String {

        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException("User not found")

        val isPasswordCorrect =
            passwordEncoder.matches(request.password, user.password)

        if (!isPasswordCorrect) {
            throw RuntimeException("Invalid credentials")
        }

        return jwtService.generateAccessToken(user.email)
    }
}

