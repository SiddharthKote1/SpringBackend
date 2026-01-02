package com.example.AgroNearBackend.Repository


import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.Entity.Wishlist
import org.springframework.data.jpa.repository.JpaRepository

interface WishlistRepository : JpaRepository<Wishlist, Long> {
    fun findByUser(user: User): List<Wishlist>
    fun findByUserAndProduct(user: User, product: Product): Wishlist?
    fun deleteByUserAndProduct(user: User, product: Product)
}
