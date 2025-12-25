package com.sleepease.insomnia.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SleepColorScheme = darkColorScheme(
    primary = WarmAmber,
    secondary = SoftOrange,
    tertiary = DeepOrange,
    background = DeepNight,
    surface = DarkBrown,
    onPrimary = DeepNight,
    onSecondary = DeepNight,
    onTertiary = DeepNight,
    onBackground = WarmAmber,
    onSurface = SoftPeach
)

@Composable
fun SleepEaseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SleepColorScheme,
        content = content
    )
}
