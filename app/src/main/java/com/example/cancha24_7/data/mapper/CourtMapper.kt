package com.example.cancha24_7.data.mapper

import com.example.cancha24_7.data.model.Court
import com.example.cancha24_7.network.dto.CourtDto

fun CourtDto.toDomain(): Court {
    return Court(
        id = id,
        name = nombre,
        city = ciudad,
        province = provincia,
        country = pais,
        address = direccion,  // 👈 NUEVO
        price = precioHora.toDoubleOrNull() ?: 0.0
    )
}