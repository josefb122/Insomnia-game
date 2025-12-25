package com.sleepease.insomnia.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sleepease.insomnia.ui.theme.*
import kotlin.math.sin
import kotlin.random.Random

// Calming visualization with gentle animations - evidence-based guided imagery
@Composable
fun VisualizationScreen(navController: NavController) {
    val infiniteTransition = rememberInfiniteTransition(label = "stars")

    // Generate random stars with warm colors
    val stars = remember {
        List(100) {
            Star(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 3f + 2f,
                color = listOf(WarmAmber, SoftOrange, GoldenGlow, SoftPeach).random(),
                speed = Random.nextFloat() * 0.5f + 0.2f,
                phase = Random.nextFloat() * 2f * Math.PI.toFloat()
            )
        }
    }

    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(60000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    val wavePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNight)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = WarmAmber,
                modifier = Modifier.size(40.dp)
            )
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Draw gentle waves at bottom - representing calm water
            for (i in 0..5) {
                val waveY = height - (i * 80f)
                val amplitude = 30f - (i * 3f)
                val points = mutableListOf<Offset>()

                for (x in 0..width.toInt() step 20) {
                    val y = waveY + amplitude * sin(
                        (x / 100f + wavePhase / 60f + i * 0.5f) * Math.PI.toFloat()
                    ).toFloat()
                    points.add(Offset(x.toFloat(), y))
                }

                for (j in 0 until points.size - 1) {
                    drawLine(
                        color = when (i) {
                            0 -> DeepOrange.copy(alpha = 0.3f)
                            1 -> SoftOrange.copy(alpha = 0.25f)
                            2 -> WarmAmber.copy(alpha = 0.2f)
                            3 -> GoldenGlow.copy(alpha = 0.15f)
                            4 -> SoftPeach.copy(alpha = 0.1f)
                            else -> MutedTan.copy(alpha = 0.05f)
                        },
                        start = points[j],
                        end = points[j + 1],
                        strokeWidth = 4f
                    )
                }
            }

            // Draw twinkling stars
            stars.forEach { star ->
                val alpha = (sin(time * star.speed + star.phase) + 1f) / 2f * 0.8f + 0.2f
                drawCircle(
                    color = star.color.copy(alpha = alpha),
                    radius = star.size,
                    center = Offset(star.x * width, star.y * height * 0.6f)
                )

                // Glow effect
                drawCircle(
                    color = star.color.copy(alpha = alpha * 0.3f),
                    radius = star.size * 2f,
                    center = Offset(star.x * width, star.y * height * 0.6f)
                )
            }

            // Draw a warm glowing moon
            val moonX = width * 0.85f
            val moonY = height * 0.2f
            val moonRadius = 80f

            // Moon glow
            drawCircle(
                color = WarmAmber.copy(alpha = 0.2f),
                radius = moonRadius * 2f,
                center = Offset(moonX, moonY)
            )

            // Moon
            drawCircle(
                color = GoldenGlow.copy(alpha = 0.8f),
                radius = moonRadius,
                center = Offset(moonX, moonY)
            )

            // Moon highlight
            drawCircle(
                color = Color.White.copy(alpha = 0.4f),
                radius = moonRadius * 0.6f,
                center = Offset(moonX - 20f, moonY - 20f)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Peaceful Night Sky",
                fontSize = 48.sp,
                fontWeight = FontWeight.Light,
                color = SoftPeach.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Watch the gentle stars twinkle...\nFeel the calm waves below...\nLet your mind drift peacefully...",
                fontSize = 24.sp,
                color = MutedTan.copy(alpha = 0.8f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 36.sp
            )
        }
    }
}

data class Star(
    val x: Float,
    val y: Float,
    val size: Float,
    val color: Color,
    val speed: Float,
    val phase: Float
)
