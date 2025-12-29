package com.example.AgroNearBackend.repository

import com.example.AgroNearBackend.Entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findByFarmerId(farmerId: Int): List<Product>
}