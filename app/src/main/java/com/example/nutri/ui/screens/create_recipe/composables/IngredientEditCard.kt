package com.example.nutri.ui.screens.create_recipe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.domain.recipes.model.Ingredient

@Composable
fun IngredientEditCard(ingredient: Ingredient, deleteItem: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 4.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(8.dp),
        content = {

            Row (horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Column {
                    Text(
                        text = ingredient.ingredientName,
                        modifier = Modifier.padding(start = 16.dp, bottom = 10.dp, top = 16.dp)
                    )

                    Row {
                        Text(
                            text = ingredient.ingredientAmount.toString(),
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                        )

                        Text (
                            text = ingredient.ingredientUnits,
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                        )
                    }
                }

                Column (Modifier.padding(end = 16.dp)){
                    IconButton(
                        onClick = { deleteItem() },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .background(Color.Transparent)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.remove48px),
                            tint = Color.White,
                            modifier = Modifier.size(32.dp),
                            contentDescription = "EditRecipe"
                        )
                    }
                }
            }
        }
    )
}