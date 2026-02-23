package com.example.cancha24_7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cancha24_7.data.repository.CanchaRepository
import com.example.cancha24_7.data.repository.HorarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CourtViewModel : ViewModel() {

    // =======================
    // CANCHAS CERCANAS
    // =======================
    private val canchaRepo = CanchaRepository()

    private val _uiState =
        MutableStateFlow<CourtUiState>(CourtUiState.Loading)
    val uiState: StateFlow<CourtUiState> = _uiState

    fun cargarCanchasCercanas(lat: Double, lon: Double) {
        _uiState.value = CourtUiState.Loading

        viewModelScope.launch {
            try {
                val canchas = canchaRepo.obtenerCanchasCercanas(lat, lon)
                _uiState.value = CourtUiState.Success(canchas)
            } catch (e: Exception) {
                _uiState.value = CourtUiState.Error(
                    e.message ?: "No se pudieron cargar las canchas"
                )
            }
        }
    }

    // =======================
    // HORARIOS
    // =======================
    private val horarioRepo = HorarioRepository()

    private val _horarioState =
        MutableStateFlow<HorarioUiState>(HorarioUiState.Idle)
    val horarioState: StateFlow<HorarioUiState> = _horarioState

    fun loadHorarios(courtId: Int, dateMillis: Long?) {
        viewModelScope.launch {
            _horarioState.value = HorarioUiState.Loading

            try {
                val fecha = SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.US
                ).format(
                    Date(dateMillis ?: System.currentTimeMillis())
                )

                val response = horarioRepo.getHorarios(courtId, fecha)

                if (response.ok && response.data != null) {

                    _horarioState.value =
                        HorarioUiState.Success(response.data)

                } else {

                    _horarioState.value =
                        HorarioUiState.Error(
                            response.error ?: "Error desconocido"
                        )
                }
            } catch (e: Exception) {
                _horarioState.value =
                    HorarioUiState.Error(
                        e.message ?: "Error cargando horarios"
                    )
            }
        }
    }
}