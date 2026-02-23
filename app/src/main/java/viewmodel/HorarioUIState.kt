package com.example.cancha24_7.viewmodel

import com.example.cancha24_7.data.model.Horario

sealed class HorarioUiState {

    object Idle : HorarioUiState()
    object Loading : HorarioUiState()

    data class Success(
        val horarios: List<Horario>
    ) : HorarioUiState()

    data class Error(
        val message: String
    ) : HorarioUiState()
}