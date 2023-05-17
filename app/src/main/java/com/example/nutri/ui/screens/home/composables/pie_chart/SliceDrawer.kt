package com.example.nutri.ui.screens.home.composables.pie_chart

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

interface SliceDrawer {
    fun draw(
        scope: DrawScope,
        canvas: androidx.compose.ui.graphics.Canvas,
        size: Size,
        startAngle: Float,
        sweepAngle: Float,
        slice: Slice
    )
}

data class Slice(
    val value: Float,
    val color: Color = Color.Gray
)