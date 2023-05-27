package com.example.nutri.ui.screens.create_recipe.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nutri.R

@Composable
fun EmptyIngredients(){
    Surface(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 10.dp),
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(24.dp)) {
            Text(
                text = LocalContext.current.getString(R.string.ingredients),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h5.fontSize
            )
            Text(text = LocalContext.current.getString(R.string.no_ingredients),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }
}