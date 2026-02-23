package com.example.cancha24_7.data.model

data class ApiResponse<T>(
    val ok: Boolean,
    val data: T?,
    val error: String?
)