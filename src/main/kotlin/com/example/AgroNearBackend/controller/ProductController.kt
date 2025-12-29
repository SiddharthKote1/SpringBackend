package com.example.AgroNearBackend.controller

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.dto.ProductRequest
import com.example.AgroNearBackend.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping
    fun addProduct(@RequestBody request: ProductRequest): Product {
        return productService.addProduct(request)
    }

    @GetMapping
    fun getAllProducts(): List<Product> {
        return productService.getAllProducts()
    }

    @GetMapping("/farmer/{farmerId}")
    fun getProductsByFarmer(@PathVariable farmerId: Int): List<Product> {
        return productService.getProductsByFarmer(farmerId)
    }
}
