package com.example.cancha24_7.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cancha24_7.CourtCard
import com.example.cancha24_7.location.UbicacionViewModel
import com.example.cancha24_7.viewmodel.CourtUiState
import com.example.cancha24_7.viewmodel.CourtViewModel


@Composable
fun UserHomeScreen(
    navController: NavController,
    ubicacionViewModel: UbicacionViewModel = viewModel(),
    courtViewModel: CourtViewModel = viewModel()

) {
    // Location state (StateFlow)
    val locationState by ubicacionViewModel.uiState.collectAsState()

    // Courts state (sealed class via StateFlow)
    val courtState by courtViewModel.uiState.collectAsState()

    // 1) Pedir permisos y arrancar ubicación
    LocationPermissionHandler(
        onPermissionGranted = {
            ubicacionViewModel.iniciarUbicacion()
        },
        onPermissionDenied = {
            // opcional: mostrás un mensaje
            // ej: snackbar / Text("Necesito ubicación para mostrar canchas cerca")
        }
    )

    // 2) Cuando llega lat/lon → cargar canchas cercanas
    LaunchedEffect(locationState.latitude, locationState.longitude) {
        val lat = locationState.latitude
        val lon = locationState.longitude
        if (lat != null && lon != null) {
            courtViewModel.cargarCanchasCercanas(lat, lon)
        }
    }

    Column {
        // (Opcional) mostrar ubicación arriba
        when {
            locationState.isLoading -> Text("Buscando ubicación 📡")
            locationState.error != null -> Text("Ubicación: ${locationState.error}")
            locationState.latitude != null -> Text(
                "📍 ${locationState.latitude}, ${locationState.longitude}"
            )
            else -> Text("Esperando permiso de ubicación 📍")
        }

        Spacer(Modifier.height(12.dp))

        // 3) Render sealed state de canchas
        when (val state = courtState) {

            is CourtUiState.Loading -> {
                Text("Buscando canchas cerca tuyo ⚽")
            }

            is CourtUiState.Error -> {
                Text("Ups: ${state.message} 😅")
            }

            is CourtUiState.Success -> {
                val courts = state.courts   // ✅ AHORA SÍ

                if (courts.isEmpty()) {
                    Text("No encontré canchas cerca 😬")
                } else {
                    LazyColumn {
                        items(courts) { cancha ->
                            CourtCard(
                                court = cancha ,
                                onClick = {
                                    // navController.navigate("court_detail/${cancha.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}