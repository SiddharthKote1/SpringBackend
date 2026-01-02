package com.example.AgroNearBackend.Entity

import jakarta.persistence.*

@Entity
@Table(
    name = "wishlist",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["user_id", "product_id"])
    ]
)
data class Wishlist(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product
)
