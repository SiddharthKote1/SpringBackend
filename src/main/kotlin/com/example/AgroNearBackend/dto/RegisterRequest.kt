package com.example.AgroNearBackend.dto

import jakarta.persistence.Column

data class RegisterRequest(
    val name:String,
    val email:String,
    val password: String
)
