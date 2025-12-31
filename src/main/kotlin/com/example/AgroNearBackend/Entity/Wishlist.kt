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
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
)