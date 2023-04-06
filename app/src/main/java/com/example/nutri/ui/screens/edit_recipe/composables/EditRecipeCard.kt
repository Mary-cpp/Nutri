package com.example.nutri.ui.screens.edit_recipe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutri.R
import com.example.nutri.ui.screens.create_recipe.composables.EmptyIngredients
import com.example.nutri.ui.screens.create_recipe.composables.IngredientEditCard
import com.example.nutri.ui.screens.edit_recipe.EditRecipeViewModel

@Composable
fun EditRecipeCard(vm: EditRecipeViewModel){

    val name = vm.nameOnEdit
    val ingredients = vm.ingredientList

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, top = 24.dp, end = 16.dp),
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        content = {
            Column(Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    TextField(modifier = Modifier
                        .padding(start = 24.dp, bottom = 16.dp)
                        .size(208.dp, 64.dp),
                        value = name.value,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                        onValueChange = { name.value = it },
                        label = { Text("Title") })

                    Column{
                        IconButton(
                            onClick = { vm.onSaveEditedRecipeButtonPressed() },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .background(Color.Transparent)
                        ){
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.save48px),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "EditRecipe"
                            )
                        }
                    }
                }
                if (ingredients.isEmpty()) { EmptyIngredients()}
                else { IngredientsToEdit(vm = vm) }
            }
        })
}

@Composable
fun IngredientsToEdit(
    vm: EditRecipeViewModel
){
    Surface(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 10.dp),
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp
    ){
        Column(Modifier.padding(24.dp)){
            Text(
                text = "Ingredients",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
            LazyColumn {
                items(items = vm.ingredientList) { ingredient ->
                    IngredientEditCard(ingredient) { vm.onRemoveButtonPressed(ingredient) }
                }
            }
        }
    }
}
