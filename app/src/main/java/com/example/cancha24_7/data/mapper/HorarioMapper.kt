package com.example.cancha24_7.data.mapper

import com.example.cancha24_7.data.model.Horario
import com.example.cancha24_7.network.dto.HorarioDto

fun HorarioDto.toDomain(): Horario {
    return Horario(
        id = id,
        horaInicio = horaInicio,
        horaFin = horaFin,
        estado = estado
    )
}