package com.example.AgroNearBackend.Entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Id

@Entity
@Table(name="product_details")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Column(nullable=false)
    val productName:String,

    @Column(nullable=false)
    val productPrice:Int,

    @Column(nullable = false)
    val imageProduct:String,

    @Column(nullable=false)
    val farmerId: Int

)
