package com.example.cancha24_7

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CourtDetailScreen(
    courtId: Int,
    navController: NavController
) {
    // después acá pedimos el Court al ViewModel usando courtId

    // ejemplo mínimo por ahora
    Text(text = "Detalle de cancha ID: $courtId")
}