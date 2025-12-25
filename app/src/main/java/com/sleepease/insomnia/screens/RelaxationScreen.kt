package com.sleepease.insomnia.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sleepease.insomnia.ui.theme.*
import kotlinx.coroutines.delay

// Progressive Muscle Relaxation (PMR) - Evidence-based anxiety reduction
@Composable
fun RelaxationScreen(navController: NavController) {
    val muscleGroups = listOf(
        "Right hand and forearm" to "Make a tight fist",
        "Right upper arm" to "Bend your elbow and flex your bicep",
        "Left hand and forearm" to "Make a tight fist",
        "Left upper arm" to "Bend your elbow and flex your bicep",
        "Forehead" to "Raise your eyebrows as high as possible",
        "Eyes and nose" to "Squeeze your eyes shut tightly",
        "Mouth and jaw" to "Clench your jaw and pull back corners of mouth",
        "Neck" to "Pull your chin down toward your chest",
        "Chest and shoulders" to "Pull shoulder blades together",
        "Abdomen" to "Tighten your stomach muscles",
        "Right thigh" to "Tighten your thigh muscles",
        "Right calf" to "Point your toes toward your head",
        "Left thigh" to "Tighten your thigh muscles",
        "Left calf" to "Point your toes toward your head"
    )

    var currentIndex by remember { mutableStateOf(0) }
    var phase by remember { mutableStateOf("Ready") }
    var countdown by remember { mutableStateOf(0) }
    var isActive by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (phase == "Tense") 1f else 0.3f,
        animationSpec = tween(500),
        label = "pulse"
    )

    LaunchedEffect(isActive) {
        if (isActive) {
            for (index in muscleGroups.indices) {
                currentIndex = index

                // Tense phase - 5 seconds
                phase = "Tense"
                for (i in 5 downTo 1) {
                    countdown = i
                    delay(1000)
                }

                // Release and relax - 10 seconds
                phase = "Release"
                for (i in 10 downTo 1) {
                    countdown = i
                    delay(1000)
                }

                delay(2000) // Transition pause
            }

            phase = "Complete"
            isActive = false
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
                text = "Progressive Muscle Relaxation",
                fontSize = 44.sp,
                fontWeight = FontWeight.Light,
                color = GoldenGlow,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Evidence-Based Tension Release",
                fontSize = 20.sp,
                color = SoftPeach,
                modifier = Modifier.padding(bottom = 64.dp)
            )

            if (!isActive && phase != "Complete") {
                Text(
                    text = "Lie down comfortably.\nTense each muscle group for 5 seconds,\nthen release and relax for 10 seconds.",
                    fontSize = 24.sp,
                    color = MutedTan,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 48.dp)
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
            } else if (phase == "Complete") {
                Text(
                    text = "Session Complete",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Medium,
                    color = GoldenGlow,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Text(
                    text = "Notice how relaxed your body feels.\nRest here as long as you'd like.",
                    fontSize = 24.sp,
                    color = SoftPeach,
                    textAlign = TextAlign.Center
                )
            } else {
                Card(
                    modifier = Modifier
                        .width(800.dp)
                        .padding(32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = CharcoalBrown.copy(alpha = alpha)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${currentIndex + 1} of ${muscleGroups.size}",
                            fontSize = 20.sp,
                            color = SoftPeach,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Text(
                            text = muscleGroups[currentIndex].first,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Medium,
                            color = WarmAmber,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        Text(
                            text = muscleGroups[currentIndex].second,
                            fontSize = 28.sp,
                            color = MutedTan,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )

                        Text(
                            text = countdown.toString(),
                            fontSize = 96.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (phase == "Tense") DeepOrange else SoftOrange
                        )

                        Text(
                            text = if (phase == "Tense") "TENSE" else "RELAX",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (phase == "Tense") DeepOrange else GoldenGlow,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            }
        }
    }
}
