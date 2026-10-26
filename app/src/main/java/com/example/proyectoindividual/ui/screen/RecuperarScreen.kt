package com.example.proyectoindividual.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VpnKey
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoindividual.ui.components.HudPanel
import com.example.proyectoindividual.ui.components.SystemButton
import com.example.proyectoindividual.ui.components.SystemChip
import com.example.proyectoindividual.ui.components.SystemTextField
import com.example.proyectoindividual.ui.components.systemBackground
import com.example.proyectoindividual.ui.theme.ProyectoIndividualTheme
import com.example.proyectoindividual.ui.theme.SysCyan
import com.example.proyectoindividual.ui.theme.SysError
import com.example.proyectoindividual.ui.theme.SysPurple
import com.example.proyectoindividual.ui.theme.SysSurface
import com.example.proyectoindividual.ui.theme.SysTextMuted

@Composable
fun RecuperarScreen(
    onBackClick: () -> Unit = {},
    onSendClick: (email: String) -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf<String?>(null) }
    var esError by remember { mutableStateOf(false) }

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
                SystemChip(text = "• SEGURIDAD // SYS-QUEST")
                Spacer(modifier = Modifier.size(40.dp))
            }

            // ---------- Contenido central ----------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Glifo central con anillo y llave
                Box(
                    modifier = Modifier.size(108.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(106.dp)
                            .border(1.dp, SysPurple.copy(alpha = 0.4f), CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .shadow(
                                elevation = 20.dp,
                                shape = CircleShape,
                                ambientColor = SysCyan,
                                spotColor = SysCyan
                            )
                            .background(Color(0xFF080D19), CircleShape)
                            .border(1.dp, SysCyan.copy(alpha = 0.6f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = null,
                            tint = SysCyan,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(32.dp)
                            .background(SysPurple, CircleShape)
                            .border(1.dp, Color(0xFFD8B4FE), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VpnKey,
                            contentDescription = "Llave",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Título y descripción
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ingresa tu correo electrónico y te enviaremos un enlace seguro para restablecerla al instante.",
                    color = SysTextMuted,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Panel HUD
                HudPanel {
                    // Aviso tipo alerta del sistema
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SysCyan.copy(alpha = 0.08f), RoundedCornerShape(8.dp))
                            .border(1.dp, SysCyan.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            tint = SysCyan,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Te enviaremos un enlace de recuperación. Revisa también tu carpeta de spam.",
                            color = Color(0xFFA5F3FC),
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            lineHeight = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    SystemTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            mensaje = null
                        },
                        label = "Correo electrónico",
                        placeholder = "tucorreo@ejemplo.com",
                        leadingIcon = Icons.Filled.Email,
                        keyboardType = KeyboardType.Email
                    )

                    if (mensaje != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = mensaje!!,
                            color = if (esError) SysError else SysCyan,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    SystemButton(
                        text = "Enviar enlace",
                        showArrow = true,
                        onClick = {
                            if (email.isBlank()) {
                                esError = true
                                mensaje = "Introduce tu correo electrónico"
                            } else {
                                esError = false
                                mensaje = "Solicitud enviada. Revisa tu bandeja de entrada."
                                onSendClick(email.trim())
                            }
                        }
                    )
                }
            }

            // ---------- Enlace de retorno ----------
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { onBackClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = SysCyan,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Volver a iniciar sesión",
                    color = SysCyan,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF05070D)
@Composable
private fun RecuperarScreenPreview() {
    ProyectoIndividualTheme {
        RecuperarScreen()
    }
}