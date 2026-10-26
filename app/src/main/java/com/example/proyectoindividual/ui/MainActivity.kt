package com.example.proyectoindividual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.proyectoindividual.ui.screen.LoginScreen
import com.example.proyectoindividual.ui.screen.RecuperarScreen
import com.example.proyectoindividual.ui.screen.RegistroScreen
import com.example.proyectoindividual.ui.theme.ProyectoIndividualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoIndividualTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Pantalla actual: "login", "recuperar" o "registro"
                    var pantalla by rememberSaveable { mutableStateOf("login") }

                    // El botón "atrás" del teléfono vuelve al login
                    BackHandler(enabled = pantalla != "login") {
                        pantalla = "login"
                    }

                    when (pantalla) {
                        "login" -> LoginScreen(
                            onLoginClick = { _, _ ->
                                // TODO: aquí irá la validación real del login
                            },
                            onForgotPasswordClick = { pantalla = "recuperar" },
                            onRegisterClick = { pantalla = "registro" }
                        )

                        "recuperar" -> RecuperarScreen(
                            onBackClick = { pantalla = "login" },
                            onSendClick = { _ ->
                                // TODO: aquí irá el envío real del enlace
                            }
                        )

                        "registro" -> RegistroScreen(
                            onBackClick = { pantalla = "login" },
                            onRegisterClick = { _, _, _ ->
                                // TODO: aquí irá el registro real
                            },
                            onLoginClick = { pantalla = "login" }
                        )
                    }
                }
            }
        }
    }
}