package com.example.cancha24_7.data.repository

import com.example.cancha24_7.data.mapper.toDomain
import com.example.cancha24_7.data.model.ApiResponse
import com.example.cancha24_7.data.model.Horario
import com.example.cancha24_7.data.source.network.RetrofitClient

class HorarioRepository {

    suspend fun getHorarios(
        canchaId: Int,
        fecha: String
    ): ApiResponse<List<Horario>> {

        val response = RetrofitClient.horarioApi
            .getHorarios(canchaId, fecha)

        return if (response.ok && response.data != null) {

            ApiResponse(
                ok = true,
                data = response.data.map { it.toDomain() },
                error = null
            )

        } else {

            ApiResponse(
                ok = false,
                data = null,
                error = response.error ?: "Error cargando horarios"
            )
        }
    }
}