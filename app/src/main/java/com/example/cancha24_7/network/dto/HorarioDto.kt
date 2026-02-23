package com.example.cancha24_7.network.dto

import com.google.gson.annotations.SerializedName

data class HorarioDto(
    val id: Int,

    @SerializedName("hora_inicio")
    val horaInicio: String,

    @SerializedName("hora_fin")
    val horaFin: String,

    val estado: String
)