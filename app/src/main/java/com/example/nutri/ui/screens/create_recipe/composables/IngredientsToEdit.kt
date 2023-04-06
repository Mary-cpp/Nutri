package com.example.nutri.ui.screens.create_recipe.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutri.ui.screens.create_recipe.CreateRecipeViewModel

@Composable
fun IngredientsToEdit(
    vm: CreateRecipeViewModel
) {
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
                text = "Ingredients",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
            LazyColumn {
                items(items = vm.listOfIngredients) { ingredient ->
                    IngredientEditCard(ingredient) { vm.onRemoveButtonPressed(ingredient) }
                }
            }
        }
    }
}