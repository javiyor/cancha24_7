package com.example.cancha24_7.data.model

data class Court(
    val id: Int,
    val name: String,
    val city: String,
    val province: String,
    val country: String,
    val address: String,   // 👈 NUEVO
    val price: Double
)