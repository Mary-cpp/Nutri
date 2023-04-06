package com.example.nutri.ui.screens.create_recipe.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutri.data.recipe.remote.dto.Characteristics
import com.example.nutri.ui.theme.NutriShape

@Composable
fun IngredientCard(ingredient: Characteristics) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 4.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = NutriShape.smallRoundedCornerShape,
        content = {
            Column {
                Text(
                    text = ingredient.foodMatch,
                    modifier = Modifier.padding(start = 16.dp, bottom = 10.dp, top = 16.dp)
                )
                Row {
                    Text(
                        text = ingredient.quantity.toString(),
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                    Text (
                        text = ingredient.measure,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }
    )
}