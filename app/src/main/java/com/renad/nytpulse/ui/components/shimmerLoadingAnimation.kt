package com.renad.nytpulse.ui.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.shimmerLoadingAnimation(): Modifier {
    val shimmerColors =
        listOf(
            Color.White.copy(alpha = 0.3f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 1.0f),
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.3f),
        )
    return this.background(
        brush =
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(x = 100f, y = 0.0f),
            end = Offset(x = 400f, y = 270f),
        ),
    )
}
