package com.example.nutri.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object NutriShape {

    val smallRoundedCornerShape : RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(8.dp)

    val mediumRoundedCornerShape : RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(16.dp)

    val largeRoundedCornerShape : RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(24.dp)
}