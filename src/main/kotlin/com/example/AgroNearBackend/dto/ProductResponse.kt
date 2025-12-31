package com.example.AgroNearBackend.dto

data class ProductResponse(
    val id: Long?,
    val productName: String,
    val productPrice: Int,
    val imageProduct: String,
    val farmerName: String
)
