package com.example.cancha24_7.network

import com.example.cancha24_7.network.dto.ApiResponseDto
import com.example.cancha24_7.network.dto.HorarioDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HorarioApiService {

    // BASE_URL ya incluye "/cancha/" en RetrofitClient
    @GET("horarios.php")
    suspend fun getHorarios(
        @Query("cancha_id") canchaId: Int,
        @Query("fecha") fecha: String
    ): ApiResponseDto<List<HorarioDto>>
}
