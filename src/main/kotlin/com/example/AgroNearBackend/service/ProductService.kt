package com.example.AgroNearBackend.service

import com.example.AgroNearBackend.Entity.Product
import com.example.AgroNearBackend.Entity.User
import com.example.AgroNearBackend.dto.ProductRequest
import com.example.AgroNearBackend.dto.ProductResponse
import com.example.AgroNearBackend.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val repo: ProductRepository
) {

    fun addProduct(req: ProductRequest, img: String, user: User): ProductResponse {
        val saved = repo.save(
            Product(
                productName = req.productName,
                productPrice = req.productPrice,
                imageProduct = img,
                user = user
            )
        )
        return ProductResponse(saved.id, saved.productName, saved.productPrice, saved.imageProduct, user.name)
    }

    fun getAll(): List<ProductResponse> =
        repo.findAll().map {
            ProductResponse(it.id, it.productName, it.productPrice, it.imageProduct, it.user.name)
        }

    fun delete(id: Long, user: User) {
        val product = repo.findById(id).orElseThrow()
        if (product.user.id != user.id) throw RuntimeException("Not owner")
        repo.delete(product)
    }
}
