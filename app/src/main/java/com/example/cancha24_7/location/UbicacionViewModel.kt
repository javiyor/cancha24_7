package com.example.cancha24_7.location

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UbicacionViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val locationService = LocationService(application)

    private val _uiState = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState

    fun iniciarUbicacion() {
        _uiState.value = LocationUiState(isLoading = true)

        locationService.startLocationUpdates { lat, lon ->
            viewModelScope.launch {
                _uiState.value = LocationUiState(
                    latitude = lat,
                    longitude = lon,
                    isLoading = false
                )
            }
        }
    }

    fun detenerUbicacion() {
        locationService.stopLocationUpdates()
    }
}