package com.example.AgroNearBackend.Entity

import jakarta.persistence.*

@Entity
@Table(name = "product_details")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val productName: String,

    @Column(nullable = false)
    val productPrice: Int,

    @Column(nullable = false)
    val imageProduct: String,

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    val farmer: User
)
