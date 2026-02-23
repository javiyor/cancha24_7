package com.example.cancha24_7.location

data class LocationUiState(
    val isLoading: Boolean = false,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val error: String? = null
)