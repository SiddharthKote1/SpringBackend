package com.example.AgroNearBackend.controller

import com.example.AgroNearBackend.dto.RegisterRequest
import com.example.AgroNearBackend.dto.LoginRequest
import com.example.AgroNearBackend.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): String {
        authService.register(request)
        return "User registered successfully"
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): String {
        authService.login(request)
        return "Login successfully"
    }
}
