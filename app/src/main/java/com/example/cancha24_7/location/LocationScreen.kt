package com.example.cancha24_7.ui.screens.location

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cancha24_7.location.UbicacionViewModel
import com.example.cancha24_7.ui.components.LocationPermissionHandler

@Composable
fun LocationScreen(
    viewModel: UbicacionViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LocationPermissionHandler(
        onPermissionDenied = {
            // Podés mostrar mensaje, snackbar, etc.
        },
        onPermissionGranted = TODO()
    )

    when {
        state.isLoading -> Text("Buscando ubicación 📡")
        state.error != null -> Text("Error: ${state.error}")
        state.latitude != null -> {
            Text("Lat: ${state.latitude}")
            Text("Lon: ${state.longitude}")
        }
        else -> Text("Esperando permiso de ubicación 📍")
    }
}