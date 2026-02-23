package com.example.cancha24_7.viewmodel.network

import com.example.cancha24_7.network.dto.ApiResponseDto
import com.example.cancha24_7.network.dto.CourtDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CanchaApiService {

    @GET("canchas.php")
    suspend fun getCanchas(
        @Query("pais") pais: String? = null,
        @Query("provincia") provincia: String? = null,
        @Query("ciudad") ciudad: String? = null
    ): ApiResponseDto<List<CourtDto>>

    @GET("canchas.php")
    suspend fun getCanchasCercanas(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): ApiResponseDto<List<CourtDto>>
}