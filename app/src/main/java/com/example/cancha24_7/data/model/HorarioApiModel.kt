package com.example.cancha24_7.data.model

import com.google.gson.annotations.SerializedName

data class HorarioApiModel(
    val id: Int,

    @SerializedName("hora_inicio")
    val horaInicio: String,

    @SerializedName("hora_fin")
    val horaFin: String,

    val estado: String
) {
    fun toDomain() {
        TODO("Not yet implemented")
    }
}