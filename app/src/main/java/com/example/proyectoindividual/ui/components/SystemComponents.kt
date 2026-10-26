package com.example.proyectoindividual.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoindividual.ui.theme.*

/** Fondo oscuro con cuadrícula holográfica y viñeta */
fun Modifier.systemBackground(): Modifier = this
    .background(SysBackground)
    .drawBehind {
        val step = 24.dp.toPx()
        val lineColor = SysCyan.copy(alpha = 0.05f)
        var x = 0f
        while (x < size.width) {
            drawLine(lineColor, Offset(x, 0f), Offset(x, size.height))
            x += step
        }
        var y = 0f
        while (y < size.height) {
            drawLine(lineColor, Offset(0f, y), Offset(size.width, y))
            y += step
        }
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.Transparent,
                    SysBackground.copy(alpha = 0.8f),
                    SysBackground
                ),
                center = center,
                radius = size.maxDimension * 0.75f
            )
        )
    }

/** Punto cian que parpadea suavemente */
@Composable
fun PulsingDot(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "pulse")
    val alpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )
    Box(
        modifier = modifier
            .size(8.dp)
            .background(SysCyan.copy(alpha = alpha), CircleShape)
    )
}

/** Etiqueta pequeña con borde cian (ej. "LVL.MAX") */
@Composable
fun SystemChip(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = SysCyan,
    bold: Boolean = false
) {
    Text(
        text = text,
        color = textColor,
        fontSize = 11.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
        letterSpacing = 0.5.sp,
        modifier = modifier
            .background(SysCyan.copy(alpha = 0.08f), RoundedCornerShape(4.dp))
            .border(1.dp, SysCyan.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    )
}

/** Panel con borde cian y marcadores en las esquinas */
@Composable
fun HudPanel(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(SysSurface.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
            .border(1.dp, SysCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
            .drawBehind {
                val len = 10.dp.toPx()
                val stroke = 2.dp.toPx()
                val w = size.width
                val h = size.height
                fun corner(x: Float, y: Float, dx: Float, dy: Float) {
                    drawLine(SysCyan, Offset(x, y), Offset(x + dx * len, y), stroke)
                    drawLine(SysCyan, Offset(x, y), Offset(x, y + dy * len), stroke)
                }
                corner(0f, 0f, 1f, 1f)
                corner(w, 0f, -1f, 1f)
                corner(0f, h, 1f, -1f)
                corner(w, h, -1f, -1f)
            }
            .padding(20.dp),
        content = content
    )
}

/** Campo de texto con etiqueta superior estilo "sistema" */
@Composable
fun SystemTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    tag: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label.uppercase(),
                color = SysCyanSoft,
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
            )
            if (tag != null) {
                Text(
                    text = tag,
                    color = Color(0xFF64748B),
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF475569),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp
                )
            },
            leadingIcon = { Icon(leadingIcon, contentDescription = null) },
            trailingIcon = trailingIcon,
            singleLine = true,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            textStyle = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 12.sp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = SysTextLight,
                unfocusedTextColor = SysTextLight,
                focusedContainerColor = SysField,
                unfocusedContainerColor = SysField,
                focusedBorderColor = SysCyan,
                unfocusedBorderColor = SysCyan.copy(alpha = 0.3f),
                focusedLeadingIconColor = SysCyan,
                unfocusedLeadingIconColor = SysCyan.copy(alpha = 0.7f),
                focusedTrailingIconColor = SysCyan,
                unfocusedTrailingIconColor = SysCyan.copy(alpha = 0.7f),
                cursorColor = SysCyan
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/** Botón principal cian de 50dp con resplandor */
@Composable
fun SystemButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showArrow: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = SysCyan,
                spotColor = SysCyan
            ),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SysCyan,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = text.uppercase(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp
        )
        if (showArrow) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

/** Separador con texto al centro ("O CONTINÚA CON") */
@Composable
fun SystemDivider(text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = SysCyan.copy(alpha = 0.2f)
        )
        Text(
            text = text.uppercase(),
            color = SysCyan.copy(alpha = 0.6f),
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = SysCyan.copy(alpha = 0.2f)
        )
    }
}

/** Botón de Google (la "G" es un marcador; el logo real va como imagen en res/drawable) */
@Composable
fun GoogleButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF334155)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = SysField,
            contentColor = Color(0xFFE2E8F0)
        )
    ) {
        Text(
            text = "G",
            color = Color(0xFF4285F4),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 0.5.sp
        )
    }
}