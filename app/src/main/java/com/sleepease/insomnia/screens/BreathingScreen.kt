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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sleepease.insomnia.ui.theme.*
import kotlinx.coroutines.delay

// 4-7-8 Breathing - Dr. Andrew Weil's technique
// Inhale: 4 seconds, Hold: 7 seconds, Exhale: 8 seconds
@Composable
fun BreathingScreen(navController: NavController) {
    var phase by remember { mutableStateOf("Ready") }
    var countdown by remember { mutableStateOf(0) }
    var isActive by remember { mutableStateOf(false) }
    var cycleCount by remember { mutableStateOf(0) }

    val animatedSize by animateFloatAsState(
        targetValue = when (phase) {
            "Inhale" -> 1f
            "Hold" -> 1f
            "Exhale" -> 0.3f
            else -> 0.5f
        },
        animationSpec = tween(
            durationMillis = when (phase) {
                "Inhale" -> 4000
                "Exhale" -> 8000
                else -> 1000
            },
            easing = LinearEasing
        ),
        label = "breathing"
    )

    LaunchedEffect(isActive) {
        if (isActive) {
            while (true) {
                // Inhale phase - 4 seconds
                phase = "Inhale"
                for (i in 4 downTo 1) {
                    countdown = i
                    delay(1000)
                }

                // Hold phase - 7 seconds
                phase = "Hold"
                for (i in 7 downTo 1) {
                    countdown = i
                    delay(1000)
                }

                // Exhale phase - 8 seconds
                phase = "Exhale"
                for (i in 8 downTo 1) {
                    countdown = i
                    delay(1000)
                }

                cycleCount++
            }
        }
    }

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

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "4-7-8 Breathing",
                fontSize = 48.sp,
                fontWeight = FontWeight.Light,
                color = GoldenGlow,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Dr. Andrew Weil's Technique",
                fontSize = 20.sp,
                color = SoftPeach,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            Box(
                modifier = Modifier.size(500.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val radius = size.minDimension / 2 * animatedSize
                    val center = Offset(size.width / 2, size.height / 2)

                    // Outer glow
                    drawCircle(
                        color = WarmAmber.copy(alpha = 0.2f),
                        radius = radius * 1.2f,
                        center = center
                    )

                    // Main circle
                    drawCircle(
                        color = when (phase) {
                            "Inhale" -> WarmAmber
                            "Hold" -> GoldenGlow
                            "Exhale" -> SoftOrange
                            else -> MutedTan
                        },
                        radius = radius,
                        center = center
                    )

                    // Inner highlight
                    drawCircle(
                        color = Color.White.copy(alpha = 0.3f),
                        radius = radius * 0.6f,
                        center = center
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = phase,
                        fontSize = 56.sp,
                        fontWeight = FontWeight.Medium,
                        color = DeepNight
                    )
                    if (isActive) {
                        Text(
                            text = countdown.toString(),
                            fontSize = 72.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNight
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            if (!isActive) {
                Button(
                    onClick = { isActive = true },
                    modifier = Modifier
                        .width(300.dp)
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CharcoalBrown,
                        contentColor = WarmAmber
                    )
                ) {
                    Text("Start", fontSize = 32.sp)
                }
            } else {
                Text(
                    text = "Cycle: $cycleCount",
                    fontSize = 28.sp,
                    color = SoftPeach
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = when (phase) {
                        "Inhale" -> "Breathe in slowly through your nose..."
                        "Hold" -> "Hold your breath gently..."
                        "Exhale" -> "Exhale completely through your mouth..."
                        else -> ""
                    },
                    fontSize = 24.sp,
                    color = MutedTan,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
        }
    }
}
