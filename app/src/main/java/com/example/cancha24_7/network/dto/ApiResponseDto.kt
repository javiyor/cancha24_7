package com.example.cancha24_7.network.dto

data class ApiResponseDto<T>(
    val ok: Boolean,
    val data: T?,
    val error: String? = null
)