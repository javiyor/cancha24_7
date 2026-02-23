package com.example.cancha24_7.data.repository

import com.example.cancha24_7.data.mapper.toDomain
import com.example.cancha24_7.data.model.Court
import com.example.cancha24_7.data.source.network.RetrofitClient

class CanchaRepository {

    suspend fun getCanchas(
        pais: String?,
        provincia: String?,
        ciudad: String?
    ): List<Court> {

        val response = RetrofitClient.canchaApi
            .getCanchas(pais, provincia, ciudad)

        if (response.ok && response.data != null) {
            return response.data
                .filter { it.activa == 1 }
                .map { it.toDomain() }
        }

        return emptyList()
    }

    suspend fun obtenerCanchasCercanas(
        lat: Double,
        lon: Double
    ): List<Court> {

        val response = RetrofitClient.canchaApi
            .getCanchasCercanas(lat, lon)

        if (response.ok && response.data != null) {
            return response.data.map { it.toDomain() }
        }

        return emptyList()
    }
}