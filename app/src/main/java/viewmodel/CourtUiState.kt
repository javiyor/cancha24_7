package com.example.cancha24_7.viewmodel

import com.example.cancha24_7.data.model.Court

sealed class CourtUiState {
    object Loading : CourtUiState()
    data class Success(val courts: List<Court>) : CourtUiState()
    data class Error(val message: String) : CourtUiState()
}