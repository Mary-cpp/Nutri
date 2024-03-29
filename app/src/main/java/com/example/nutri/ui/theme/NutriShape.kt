package com.example.nutri.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object NutriShape {

    val nutrientCardCornerShape: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(6.dp)

    val smallRoundedCornerShape : RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(8.dp)

    val mediumRoundedCornerShape : RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(16.dp)

    val largeRoundedCornerShape : RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(24.dp)

    val smallRoundCornerShape : RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(32.dp)

    val mealsListCornerShape : RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
}