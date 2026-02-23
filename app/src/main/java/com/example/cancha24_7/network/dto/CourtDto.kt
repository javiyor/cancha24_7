package com.example.cancha24_7.network.dto

import com.google.gson.annotations.SerializedName

data class CourtDto(
    val id: Int,
    val nombre: String,
    val tipo: String,
    @SerializedName("precio_hora")
    val precioHora: String,
    val ciudad: String,
    val provincia: String,
    val pais: String,
    val direccion: String,
    val activa: Int
)