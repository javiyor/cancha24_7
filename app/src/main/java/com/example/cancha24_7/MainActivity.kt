package com.example.cancha24_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cancha24_7.ui.components.UserHomeScreen
import com.example.cancha24_7.ui.theme.Cancha24_7Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Cancha24_7Theme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "welcome"
                ) {

                    // ---------- WELCOME ----------
                    composable("welcome") {
                        WelcomeScreen(navController)
                    }

                    // ---------- HOME USUARIO ----------
                    composable("user_home") {
                        UserHomeScreen(
                            navController = navController
                        )
                    }

                    // ---------- LOGIN ADMIN ----------
                    composable("admin_login") {
                        AdminLoginScreen(navController)
                    }

                    // ---------- HOME ADMIN ----------
                    composable("admin_home") {
                        AdminHomeScreen(navController)
                    }

                    // ---------- DETALLE CANCHA ----------
                    composable("court_detail/{courtId}") { backStackEntry ->
                        val courtId =
                            backStackEntry.arguments
                                ?.getString("courtId")
                                ?.toIntOrNull()

                        if (courtId != null) {
                            CourtDetailScreen(
                                courtId = courtId,
                                navController = navController
                            )
                        }
                    }

                    // ---------- RESERVA ----------
                    composable("booking/{courtId}") { backStackEntry ->
                        val courtId =
                            backStackEntry.arguments
                                ?.getString("courtId")
                                ?.toIntOrNull()

                        if (courtId != null) {
                            BookingScreen(
                                navController = navController,
                                courtId = courtId
                            )
                        }
                    }
                }
            }
        }
    }
}