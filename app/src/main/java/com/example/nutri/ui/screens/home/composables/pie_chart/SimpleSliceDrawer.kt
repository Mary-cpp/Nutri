package com.example.nutri.ui.screens.home.composables.pie_chart

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope

class SimpleSliceDrawer (private val sliceThickness: Float = 25f) : SliceDrawer {
    init {
        require(sliceThickness in 10f..100f) {
            "Thickness of $sliceThickness must be between 10-100"
        }
    }

    private val sectionPaint = Paint().apply {
        isAntiAlias = true
        style = PaintingStyle.Stroke
    }

    override fun draw(
        scope: DrawScope,
        canvas: Canvas,
        size: Size,
        startAngle: Float,
        sweepAngle: Float,
        slice: Slice
    ) {
        val sliceThickness = calculateSectorThickness(area = size)
        val drawableArea = calculateDrawableArea(area = size)

        canvas.drawArc(
            rect = drawableArea,
            paint = sectionPaint.apply {
                color = slice.color
                strokeWidth = sliceThickness
            },
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false
        )
    }

    private fun calculateSectorThickness(area: Size): Float {
        val minSize = minOf(area.width, area.height)

        return minSize * (sliceThickness / 200f)
    }

    private fun calculateDrawableArea(area: Size): Rect {
        val sliceThicknessOffset =
            calculateSectorThickness(area = area) / 2f
        val offsetHorizontally = (area.width - area.height) / 2f

        return Rect(
            left = sliceThicknessOffset + offsetHorizontally,
            top = sliceThicknessOffset,
            right = area.width - sliceThicknessOffset - offsetHorizontally,
            bottom = area.height - sliceThicknessOffset
        )
    }
}