package com.example.nutri.ui.screens.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(topBarText: String) {
    TopAppBar(title = { Text(text = topBarText, color = Color.Black) },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp)
}