package com.example.AgroNearBackend.controller

import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.dto.WishlistResponse
import com.example.AgroNearBackend.service.WishlistService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wishlist")
class WishlistController(
    private val wishlistService: WishlistService
) {

    @PostMapping("/{productId}")
    fun addToWishlist(@PathVariable productId: Long) {
        val user = getLoggedInUser()
        wishlistService.addToWishlist(user, productId)
    }

    @GetMapping
    fun getWishlist(): List<WishlistResponse> {
        val user = getLoggedInUser()
        return wishlistService.getWishlist(user)
    }

    @DeleteMapping("/{productId}")
    fun removeFromWishlist(@PathVariable productId: Long) {
        val user = getLoggedInUser()
        wishlistService.removeFromWishlist(user, productId)
    }

    private fun getLoggedInUser(): User {
        val auth = SecurityContextHolder.getContext().authentication
                as UsernamePasswordAuthenticationToken
        return auth.principal as User
    }
}
