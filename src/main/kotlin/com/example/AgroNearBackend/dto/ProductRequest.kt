package com.example.AgroNearBackend.dto

data class ProductRequest(
    val productName:String,
    val productPrice: Int,
    val imageProduct:String,
    val farmerId: Int
)
