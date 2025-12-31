package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.Entity.Wishlist
import com.example.AgroNearBackend.Repository.WishlistRepository
import com.example.AgroNearBackend.dto.WishlistResponse
import com.example.AgroNearBackend.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class WishlistService(
    private val wishlistRepository: WishlistRepository,
    private val productRepository: ProductRepository
) {

    fun addToWishlist(user: User, productId: Long) {

        val product = productRepository.findById(productId)
            .orElseThrow { RuntimeException("Product not found") }

        val existing = wishlistRepository.findByUserAndProduct(user, product)
        if (existing == null) {
            wishlistRepository.save(
                Wishlist(
                    user = user,
                    product = product
                )
            )
        }
    }

    fun getWishlist(user: User): List<WishlistResponse> {
        return wishlistRepository.findByUser(user).map {
            WishlistResponse(
                productId = it.product.id,
                productName = it.product.productName,
                productPrice = it.product.productPrice,
                productImage = it.product.imageProduct,
                farmerName = it.product.farmer.name
            )
        }
    }

    fun removeFromWishlist(user: User, productId: Long) {

        val product = productRepository.findById(productId)
            .orElseThrow { RuntimeException("Product not found") }

        wishlistRepository.deleteByUserAndProduct(user, product)
    }
}
