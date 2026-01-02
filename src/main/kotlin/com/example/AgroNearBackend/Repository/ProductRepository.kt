package com.example.AgroNearBackend.repository

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.Entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findByUser(user: User): List<Product>
}
