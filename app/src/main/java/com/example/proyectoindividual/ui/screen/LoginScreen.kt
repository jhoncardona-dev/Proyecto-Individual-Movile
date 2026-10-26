package com.example.proyectoindividual.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoindividual.ui.components.GoogleButton
import com.example.proyectoindividual.ui.components.HudPanel
import com.example.proyectoindividual.ui.components.PulsingDot
import com.example.proyectoindividual.ui.components.SystemButton
import com.example.proyectoindividual.ui.components.SystemChip
import com.example.proyectoindividual.ui.components.SystemDivider
import com.example.proyectoindividual.ui.components.SystemTextField
import com.example.proyectoindividual.ui.components.systemBackground
import com.example.proyectoindividual.ui.theme.ProyectoIndividualTheme
import com.example.proyectoindividual.ui.theme.SysCyan
import com.example.proyectoindividual.ui.theme.SysCyanSoft
import com.example.proyectoindividual.ui.theme.SysError
import com.example.proyectoindividual.ui.theme.SysPurple
import com.example.proyectoindividual.ui.theme.SysTextMuted

@Composable
fun LoginScreen(
    onLoginClick: (email: String, password: String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .systemBackground()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        val alturaPantalla = maxHeight

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = alturaPantalla)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // ---------- Header de estado ----------
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PulsingDot()
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "SISTEMA // ONLINE",
                            color = SysCyan.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.5.sp
                        )
                    }
                    SystemChip(text = "LVL.MAX", textColor = SysCyanSoft, bold = true)
                }
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = SysCyan.copy(alpha = 0.2f))
            }

            // ---------- Contenido central ----------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Emblema
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .shadow(
                            elevation = 20.dp,
                            shape = RoundedCornerShape(16.dp),
                            ambientColor = SysCyan,
                            spotColor = SysCyan
                        )
                        .background(Color(0xFF090E1A), RoundedCornerShape(16.dp))
                        .border(1.dp, SysCyan.copy(alpha = 0.45f), RoundedCornerShape(16.dp))
                        .padding(4.dp)
                        .border(1.dp, SysPurple.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Security,
                        contentDescription = "Logo de la aplicación",
                        tint = SysCyan,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Título
                SystemChip(text = "[ SISTEMA DE AUTENTICACIÓN ]")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "¡Bienvenido de nuevo!",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ingresa tus credenciales para continuar",
                    color = SysTextMuted,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Panel HUD
                HudPanel {
                    SystemTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            mensajeError = null
                        },
                        label = "Correo electrónico",
                        tag = "// ID_HUNTER",
                        placeholder = "tucorreo@ejemplo.com",
                        leadingIcon = Icons.Filled.Email,
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SystemTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            mensajeError = null
                        },
                        label = "Contraseña",
                        tag = "// PASSKEY",
                        placeholder = "••••••••",
                        leadingIcon = Icons.Filled.Lock,
                        keyboardType = KeyboardType.Password,
                        visualTransformation = if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Filled.Visibility
                                    else Icons.Filled.VisibilityOff,
                                    contentDescription = if (passwordVisible) "Ocultar contraseña"
                                    else "Mostrar contraseña"
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        color = SysCyan,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { onForgotPasswordClick() }
                    )

                    if (mensajeError != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = mensajeError!!,
                            color = SysError,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    SystemButton(
                        text = "Iniciar Sesión",
                        onClick = {
                            if (email.isBlank() || password.isBlank()) {
                                mensajeError = "Completa tu correo y tu contraseña"
                            } else {
                                mensajeError = null
                                onLoginClick(email.trim(), password)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SystemDivider(text = "O continúa con")

                    Spacer(modifier = Modifier.height(20.dp))

                    GoogleButton(
                        text = "Continuar con Google",
                        onClick = { /* Aquí irá la acción de Google */ }
                    )
                }
            }

            // ---------- Footer ----------
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¿No tienes una cuenta? ",
                    color = SysTextMuted,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "Regístrate",
                    color = SysCyan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF05070D)
@Composable
private fun LoginScreenPreview() {
    ProyectoIndividualTheme {
        LoginScreen()
    }
}