package com.example.proyectoindividual.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SystemColorScheme = darkColorScheme(
    primary = SysCyan,
    onPrimary = SysBackground,
    primaryContainer = SysField,
    onPrimaryContainer = SysCyanSoft,
    secondary = SysPurple,
    onSecondary = SysTextLight,
    background = SysBackground,
    onBackground = SysTextLight,
    surface = SysSurface,
    onSurface = SysTextLight,
    error = SysError
)

@Composable
fun ProyectoIndividualTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = SystemColorScheme,
        typography = Typography,
        content = content
    )
}

