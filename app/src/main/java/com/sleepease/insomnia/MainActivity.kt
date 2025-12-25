package com.sleepease.insomnia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sleepease.insomnia.screens.*
import com.sleepease.insomnia.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SleepEaseTheme {
                SleepEaseApp()
            }
        }
    }
}

@Composable
fun SleepEaseApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("breathing") { BreathingScreen(navController) }
        composable("relaxation") { RelaxationScreen(navController) }
        composable("visualization") { VisualizationScreen(navController) }
        composable("counting") { CountingScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: androidx.navigation.NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNight),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "SleepEase",
                fontSize = 56.sp,
                fontWeight = FontWeight.Light,
                color = GoldenGlow,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Evidence-Based Sleep Companion",
                fontSize = 20.sp,
                color = SoftPeach,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            ActivityButton(
                title = "4-7-8 Breathing",
                description = "Clinically proven breathing technique",
                onClick = { navController.navigate("breathing") }
            )

            ActivityButton(
                title = "Muscle Relaxation",
                description = "Progressive relaxation guide",
                onClick = { navController.navigate("relaxation") }
            )

            ActivityButton(
                title = "Calming Visualization",
                description = "Peaceful animated imagery",
                onClick = { navController.navigate("visualization") }
            )

            ActivityButton(
                title = "Mindful Counting",
                description = "Meditative counting exercise",
                onClick = { navController.navigate("counting") }
            )
        }
    }
}

@Composable
fun ActivityButton(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(600.dp)
            .height(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = CharcoalBrown,
            contentColor = WarmAmber
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = description,
                fontSize = 16.sp,
                color = SoftPeach
            )
        }
    }
}
