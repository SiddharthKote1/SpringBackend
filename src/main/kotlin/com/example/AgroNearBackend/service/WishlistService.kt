package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.Entity.Wishlist
import com.example.AgroNearBackend.Repository.WishlistRepository
import com.example.AgroNearBackend.dto.WishlistResponse
import com.example.AgroNearBackend.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class WishlistService(
    private val repo: WishlistRepository,
    private val productRepo: ProductRepository
) {

    fun add(user: User, productId: Long) {
        val product = productRepo.findById(productId).orElseThrow()
        if (repo.findByUserAndProduct(user, product) == null) {
            repo.save(Wishlist(user = user, product = product))
        }
    }

    fun get(user: User): List<WishlistResponse> =
        repo.findByUser(user).map {
            WishlistResponse(
                it.product.id,
                it.product.productName,
                it.product.productPrice,
                it.product.imageProduct,
                it.product.user.name
            )
        }
}
