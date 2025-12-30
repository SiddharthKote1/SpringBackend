package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.dto.ProductRequest
import com.example.AgroNearBackend.repository.ProductRepository
import jakarta.websocket.server.ServerEndpoint
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun addProduct(request: ProductRequest): Product {

        val product = Product(
            productName = request.productName,
            productPrice = request.productPrice,
            imageProduct = request.imageProduct,
            farmerId = request.farmerId
        )

        return productRepository.save(product)
    }

    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun getProductsByFarmer(farmerId: Int): List<Product> {
        return productRepository.findByFarmerId(farmerId)
    }
}