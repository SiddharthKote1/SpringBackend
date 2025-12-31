package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.dto.ProductRequest
import com.example.AgroNearBackend.dto.ProductResponse
import com.example.AgroNearBackend.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    // ✅ CREATE PRODUCT
    fun addProduct(
        request: ProductRequest,
        imagePath: String,
        farmer: User
    ): Product {

        val product = Product(
            productName = request.productName,
            productPrice = request.productPrice,
            imageProduct = imagePath,
            farmer = farmer
        )

        return productRepository.save(product)
    }

    // ✅ GET ALL PRODUCTS
    fun getAllProducts(): List<ProductResponse> {
        return productRepository.findAll().map { product ->
            ProductResponse(
                id = product.id,
                productName = product.productName,
                productPrice = product.productPrice,
                imageProduct = product.imageProduct,
                farmerName = product.farmer.name
            )
        }
    }

    // ✅ GET FARMER PRODUCTS
    fun getProductsByFarmer(farmer: User): List<ProductResponse> {
        return productRepository.findByFarmer(farmer).map { product ->
            ProductResponse(
                id = product.id,
                productName = product.productName,
                productPrice = product.productPrice,
                imageProduct = product.imageProduct,
                farmerName = farmer.name
            )
        }
    }

    // ✅ UPDATE PRODUCT
    fun updateProduct(
        productId: Long,
        request: ProductRequest,
        farmer: User
    ): ProductResponse {

        val product = productRepository.findById(productId)
            .orElseThrow { RuntimeException("Product not found") }

        if (product.farmer.id != farmer.id) {
            throw RuntimeException("You are not allowed to update this product")
        }

        val updated = product.copy(
            productName = request.productName,
            productPrice = request.productPrice
        )

        val saved = productRepository.save(updated)

        return ProductResponse(
            id = saved.id,
            productName = saved.productName,
            productPrice = saved.productPrice,
            imageProduct = saved.imageProduct,
            farmerName = saved.farmer.name
        )
    }

    // ✅ DELETE PRODUCT
    fun deleteProduct(productId: Long, farmer: User) {

        val product = productRepository.findById(productId)
            .orElseThrow { RuntimeException("Product not found") }

        if (product.farmer.id != farmer.id) {
            throw RuntimeException("You are not allowed to delete this product")
        }

        productRepository.delete(product)
    }
}
