package com.example.cancha24_7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cancha24_7.viewmodel.CourtUiState
import com.example.cancha24_7.viewmodel.CourtViewModel
import com.example.cancha24_7.viewmodel.HorarioUiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    navController: NavController,
    courtId: Int,
    courtViewModel: CourtViewModel = viewModel()
) {

    // ---------- ESTADOS ----------
    var showDatePicker by remember { mutableStateOf(false) }
    var horarioSeleccionado by remember { mutableStateOf<String?>(null) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    fun Long?.toFormattedDate(): String {
        return if (this != null) {
            val date = Date(this)
            val format = SimpleDateFormat(
                "EEEE, dd 'de' MMMM 'de' yyyy",
                Locale("es", "AR")
            )
            format.format(date)
        } else {
            "Sin fecha"
        }
    }

    val uiState by courtViewModel.uiState.collectAsState()

    val court = when (uiState) {
        is CourtUiState.Success ->
            (uiState as CourtUiState.Success).courts.find { it.id == courtId }

        else -> null
    }
    // ---------- UI ----------
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Realizar Reserva") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ---------- VALIDACIÓN ----------
            if (court == null) {
                Text(
                    text = "Cancha no encontrada",
                    color = MaterialTheme.colorScheme.error
                )
                return@Column
            }

            // ---------- HEADER ----------
            Text("Reservando:", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))

            Text(
                text = court.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(24.dp))

            // ---------- FECHA ----------
            Text("Fecha seleccionada", style = MaterialTheme.typography.titleMedium)
            Text(
                text = datePickerState.selectedDateMillis.toFormattedDate(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            OutlinedButton(onClick = { showDatePicker = true }) {
                Text("Cambiar fecha")
            }

            Spacer(Modifier.height(24.dp))

            // ---------- BUSCAR HORARIOS ----------
            Button(
                onClick = {
                    horarioSeleccionado = null
                    courtViewModel.loadHorarios(
                        courtId = court.id,
                        dateMillis = datePickerState.selectedDateMillis
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("BUSCAR HORAS DISPONIBLES")
            }

            Spacer(Modifier.height(24.dp))

            // ---------- HORARIOS ----------
            val horarioState by courtViewModel.horarioState.collectAsState()

            when (val state = horarioState) {
                HorarioUiState.Idle -> {}

                HorarioUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is HorarioUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is HorarioUiState.Success -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
					for (horario in state.horarios) {

                            val esDisponible = horario.estado.uppercase() == "DISPONIBLE"
                            val rangoHora = "${horario.horaInicio} - ${horario.horaFin}"

                            OutlinedButton(
                                onClick = {
                                    horarioSeleccionado = rangoHora
                                },
                                enabled = esDisponible,
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor =
                                        if (horarioSeleccionado == rangoHora)
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                        else
                                            MaterialTheme.colorScheme.surface
                                )
                            ) {
                                Text(
                                    text = buildString {
                                        append(rangoHora)
                                        if (!esDisponible) append(" (Ocupado)")
                                    },
                                    fontWeight =
                                        if (horarioSeleccionado == rangoHora)
                                            FontWeight.Bold
                                        else
                                            FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }

            // ---------- CONFIRMAR ----------
            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    // 🔜 siguiente paso:
                    // confirmar reserva / navegar a resumen
                },
                enabled = horarioSeleccionado != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("CONFIRMAR RESERVA")
            }

            // ---------- DATE PICKER ----------
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text("Cancelar")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}