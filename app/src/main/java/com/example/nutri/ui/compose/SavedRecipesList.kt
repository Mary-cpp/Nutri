package com.example.nutri.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.model.Recipe

@Composable
fun SavedRecipesList(recipes: MutableList<Recipe>) {
    Column {
        recipes.forEach { message ->
            Recipe(message,
            modifier = Modifier.padding(8.dp))
        }
    }
}