package com.example.cancha24_7.data

data class Reserva(
    val id: Int,
    val horarioId: Int,
    val usuarioId: Int,
    val estado: String,
    val metodoPago: String?,
    val comprobanteUrl: String?
)
