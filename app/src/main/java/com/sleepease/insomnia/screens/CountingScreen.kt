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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sleepease.insomnia.ui.theme.*
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

// Mindful counting meditation - slow counting with visual mandalas
@Composable
fun CountingScreen(navController: NavController) {
    var count by remember { mutableStateOf(1) }
    var isActive by remember { mutableStateOf(false) }

    val animatedProgress by animateFloatAsState(
        targetValue = if (isActive) 1f else 0f,
        animationSpec = tween(3000, easing = LinearEasing),
        label = "progress"
    )

    val infiniteRotation = rememberInfiniteTransition(label = "rotation")
    val rotation by infiniteRotation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(40000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotate"
    )

    LaunchedEffect(isActive) {
        if (isActive) {
            while (true) {
                delay(3500) // Count every 3.5 seconds - slow enough for meditation
                count++
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
                text = "Mindful Counting",
                fontSize = 48.sp,
                fontWeight = FontWeight.Light,
                color = GoldenGlow,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Meditative Number Awareness",
                fontSize = 20.sp,
                color = SoftPeach,
                modifier = Modifier.padding(bottom = 64.dp)
            )

            Box(
                modifier = Modifier.size(600.dp),
                contentAlignment = Alignment.Center
            ) {
                // Draw rotating mandala pattern
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val center = Offset(size.width / 2, size.height / 2)
                    val radius = size.minDimension / 2 - 50f

                    // Draw concentric circles
                    for (i in 1..5) {
                        val r = radius * i / 5f
                        drawCircle(
                            color = WarmAmber.copy(alpha = 0.1f * (6 - i)),
                            radius = r,
                            center = center,
                            style = Stroke(width = 2f)
                        )
                    }

                    // Draw radiating lines
                    val petals = 12
                    for (i in 0 until petals) {
                        val angle = (rotation + i * 360f / petals) * Math.PI.toFloat() / 180f
                        val startX = center.x + cos(angle) * radius * 0.3f
                        val startY = center.y + sin(angle) * radius * 0.3f
                        val endX = center.x + cos(angle) * radius
                        val endY = center.y + sin(angle) * radius

                        drawLine(
                            color = SoftOrange.copy(alpha = 0.4f),
                            start = Offset(startX, startY),
                            end = Offset(endX, endY),
                            strokeWidth = 3f
                        )

                        // Draw petal shapes
                        val path = Path().apply {
                            moveTo(center.x, center.y)
                            val controlAngle1 = angle - 0.3f
                            val controlAngle2 = angle + 0.3f
                            cubicTo(
                                center.x + cos(controlAngle1) * radius * 0.7f,
                                center.y + sin(controlAngle1) * radius * 0.7f,
                                center.x + cos(controlAngle2) * radius * 0.7f,
                                center.y + sin(controlAngle2) * radius * 0.7f,
                                center.x + cos(angle) * radius * 0.8f,
                                center.y + sin(angle) * radius * 0.8f
                            )
                        }

                        drawPath(
                            path = path,
                            color = DeepOrange.copy(alpha = 0.15f)
                        )
                    }

                    // Center glow
                    drawCircle(
                        color = GoldenGlow.copy(alpha = 0.3f),
                        radius = radius * 0.25f,
                        center = center
                    )
                }

                // Count number in center
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = count.toString(),
                        fontSize = 128.sp,
                        fontWeight = FontWeight.Bold,
                        color = WarmAmber
                    )

                    if (isActive) {
                        LinearProgressIndicator(
                            progress = { animatedProgress },
                            modifier = Modifier
                                .width(200.dp)
                                .padding(top = 24.dp),
                            color = SoftOrange,
                            trackColor = CharcoalBrown
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            if (!isActive) {
                Text(
                    text = "Focus on each number as it appears.\nLet thoughts drift away with each count.",
                    fontSize = 22.sp,
                    color = MutedTan,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

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
                    Text("Begin", fontSize = 32.sp)
                }
            } else {
                Text(
                    text = "Breathe... and count...",
                    fontSize = 28.sp,
                    color = SoftPeach,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
