package com.example.cancha24_7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cancha24_7.ui.theme.Cancha24_7Theme

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido a Cancha24/7", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Reserva tu cancha, a tu manera.", fontSize = 16.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(48.dp))
        Text("¿Cómo quieres continuar?", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("user_home") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Soy un Jugador")
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { navController.navigate("admin_login") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Soy Administrador de un Complejo")
        }
    }
}

@Preview(showBackground = true, name = "Pantalla de Bienvenida")
@Composable
fun WelcomeScreenPreview() {
    Cancha24_7Theme {
        WelcomeScreen(navController = rememberNavController())
    }
}