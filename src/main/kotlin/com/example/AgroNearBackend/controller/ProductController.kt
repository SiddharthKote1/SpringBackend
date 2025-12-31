package com.example.AgroNearBackend.controller

import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.dto.ProductRequest
import com.example.AgroNearBackend.dto.ProductResponse
import com.example.AgroNearBackend.service.ProductService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    // ✅ CREATE PRODUCT + IMAGE UPLOAD (FARMER)
    @PostMapping(consumes = ["multipart/form-data"])
    fun addProduct(
        @RequestParam productName: String,
        @RequestParam productPrice: Int,
        @RequestParam image: MultipartFile
    ) {

        val auth = SecurityContextHolder.getContext().authentication
                as UsernamePasswordAuthenticationToken
        val farmer = auth.principal as User

        val uploadDir = Paths.get("uploads/products")
        Files.createDirectories(uploadDir)

        val fileName = image.originalFilename ?: "product.jpg"
        Files.write(uploadDir.resolve(fileName), image.bytes)

        val request = ProductRequest(
            productName = productName,
            productPrice = productPrice
        )

        productService.addProduct(
            request,
            "uploads/products/$fileName",
            farmer
        )
    }

    // ✅ GET ALL PRODUCTS (BUYER + FARMER)
    @GetMapping
    fun getAllProducts(): List<ProductResponse> {
        return productService.getAllProducts()
    }

    // ✅ GET PRODUCTS OF LOGGED-IN FARMER
    @GetMapping("/farmer")
    fun getProductsByFarmer(): List<ProductResponse> {

        val auth = SecurityContextHolder.getContext().authentication
                as UsernamePasswordAuthenticationToken
        val farmer = auth.principal as User

        return productService.getProductsByFarmer(farmer)
    }

    // ✅ UPDATE PRODUCT (ONLY OWNER FARMER)
    @PutMapping("/{productId}")
    fun updateProduct(
        @PathVariable productId: Long,
        @RequestBody request: ProductRequest
    ): ProductResponse {

        val auth = SecurityContextHolder.getContext().authentication
                as UsernamePasswordAuthenticationToken
        val farmer = auth.principal as User

        return productService.updateProduct(productId, request, farmer)
    }

    // ✅ DELETE PRODUCT (ONLY OWNER FARMER)
    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: Long) {

        val auth = SecurityContextHolder.getContext().authentication
                as UsernamePasswordAuthenticationToken
        val farmer = auth.principal as User

        productService.deleteProduct(productId, farmer)
    }
}
