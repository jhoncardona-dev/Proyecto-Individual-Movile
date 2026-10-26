package com.example.proyectoindividual.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import com.example.proyectoindividual.ui.components.SystemButton
import com.example.proyectoindividual.ui.components.SystemChip
import com.example.proyectoindividual.ui.components.SystemDivider
import com.example.proyectoindividual.ui.components.SystemTextField
import com.example.proyectoindividual.ui.components.systemBackground
import com.example.proyectoindividual.ui.theme.ProyectoIndividualTheme
import com.example.proyectoindividual.ui.theme.SysCyan
import com.example.proyectoindividual.ui.theme.SysError
import com.example.proyectoindividual.ui.theme.SysSurface
import com.example.proyectoindividual.ui.theme.SysTextLight
import com.example.proyectoindividual.ui.theme.SysTextMuted

@Composable
fun RegistroScreen(
    onBackClick: () -> Unit = {},
    onRegisterClick: (nombre: String, correo: String, password: String) -> Unit = { _, _, _ -> },
    onLoginClick: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmar by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var aceptaTerminos by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBackground()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ---------- Barra superior ----------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(40.dp)
                        .background(SysSurface, RoundedCornerShape(8.dp))
                        .border(1.dp, SysCyan.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                ) {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = "Volver",
                        tint = SysCyan
                    )
                }
                SystemChip(text = "• SISTEMA // REGISTRO")
                Spacer(modifier = Modifier.size(40.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ---------- Título ----------
            Text(
                text = "Crea tu cuenta",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Empieza tu viaje con nosotros",
                color = SysTextMuted,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ---------- Panel HUD ----------
            HudPanel {
                SystemTextField(
                    value = nombre,
                    onValueChange = {
                        nombre = it
                        mensajeError = null
                    },
                    label = "Nombre completo",
                    tag = "// HUNTER_NAME",
                    placeholder = "Tu nombre",
                    leadingIcon = Icons.Filled.Person
                )

                Spacer(modifier = Modifier.height(16.dp))

                SystemTextField(
                    value = correo,
                    onValueChange = {
                        correo = it
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

                Spacer(modifier = Modifier.height(16.dp))

                SystemTextField(
                    value = confirmar,
                    onValueChange = {
                        confirmar = it
                        mensajeError = null
                    },
                    label = "Confirmar contraseña",
                    tag = "// CONFIRM",
                    placeholder = "••••••••",
                    leadingIcon = Icons.Filled.Lock,
                    keyboardType = KeyboardType.Password,
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Términos y condiciones
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = aceptaTerminos,
                        onCheckedChange = {
                            aceptaTerminos = it
                            mensajeError = null
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = SysCyan,
                            uncheckedColor = SysCyan.copy(alpha = 0.5f),
                            checkmarkColor = Color.Black
                        )
                    )
                    Text(
                        text = "Acepto los términos y condiciones",
                        color = SysTextLight,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                if (mensajeError != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mensajeError!!,
                        color = SysError,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                SystemButton(
                    text = "Crear cuenta",
                    onClick = {
                        when {
                            nombre.isBlank() || correo.isBlank() ||
                                password.isBlank() || confirmar.isBlank() ->
                                mensajeError = "Completa todos los campos"

                            password.length < 6 ->
                                mensajeError = "La contraseña debe tener al menos 6 caracteres"

                            password != confirmar ->
                                mensajeError = "Las contraseñas no coinciden"

                            !aceptaTerminos ->
                                mensajeError = "Debes aceptar los términos y condiciones"

                            else -> {
                                mensajeError = null
                                onRegisterClick(nombre.trim(), correo.trim(), password)
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                SystemDivider(text = "O regístrate con")

                Spacer(modifier = Modifier.height(20.dp))

                GoogleButton(
                    text = "Continuar con Google",
                    onClick = { /* Aquí irá el registro con Google */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ---------- Footer ----------
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "¿Ya tienes una cuenta? ",
                    color = SysTextMuted,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "Inicia sesión",
                    color = SysCyan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF05070D)
@Composable
private fun RegistroScreenPreview() {
    ProyectoIndividualTheme {
        RegistroScreen()
    }
}