package com.example.nutri.ui.screens.create_recipe.composables

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Labels(
    backgroundColor: Color,
    cornerShape: RoundedCornerShape,
    labels: List<String>
) {
    Row(
        Modifier
            .padding(bottom = 8.dp)
            .horizontalScroll(ScrollState(0))
    ) {
        labels.forEach {
            Card(
                modifier = Modifier.padding(end = 8.dp),
                backgroundColor = backgroundColor,
                elevation = 6.dp,
                shape = cornerShape
            ) {
                Text(
                    text = it,
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                )
            }
        }
    }
}
