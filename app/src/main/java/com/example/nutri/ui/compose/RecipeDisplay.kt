package com.example.nutri.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutri.domain.model.Recipe
import com.example.nutri.ui.theme.NutriTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OldRecipeCard(recipe: Recipe){
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(1f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ){

        Text(text = recipe.ingredients!![0]!!.text,
            modifier = Modifier.padding(top=24.dp, start = 12.dp, bottom = 24.dp),
            fontSize = 20.sp)

        Text(
            text = "Calories: ${recipe.calories}",
            modifier = Modifier.padding(start = 24.dp, bottom = 12.dp)
        )

        Text(
                text = "Total weight: ${recipe.totalWeight}",
        modifier = Modifier.padding(start = 24.dp, bottom = 12.dp)
        )
    }
}

@Composable
fun RecipeDisplay(recipe: Recipe) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(text = "URL: ${recipe.uri}", modifier = Modifier.padding(bottom = 8.dp))

        Text(
            text = "Calories: ${recipe.calories}"
        )

        if(recipe.ingredients!= null){

            val ingredients = recipe.ingredients[0]

            val ingredientsList = ingredients?.parsed

            Text(
                text = "Ingredients:",
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(text = "Name: ${recipe.ingredients[0]!!.text}",
            modifier = Modifier.padding(top=16.dp))

            repeat(ingredientsList!!.size){

                Column(modifier = Modifier.padding(start = 32.dp)) {
                    Text(text = "Info: ${ingredientsList[it].food}",
                        modifier = Modifier.padding(top=16.dp),
                    textDecoration = TextDecoration.Underline)

                    Text(text = "Weight: ${ingredientsList[it].weight}",
                        modifier = Modifier.padding(top=16.dp))

                    Text(text = "Measure:\n" +
                            " ${ingredientsList[it].measure}",
                        modifier = Modifier.padding(top=16.dp, bottom = 8.dp))
                }
            }
        }
    }
}

@Composable
fun MyRecipesDisplay(onGoHome: () -> Unit, recipeList : List<Recipe>){
    Button(onClick = {onGoHome.invoke()}) {
        Text(text = "Go home")
    }

    recipeList.forEach {
        OldRecipeCard(recipe = it)
    }
}

@Preview
@Composable
fun RecipePreview(){
    NutriTheme {
        OldRecipeCard(recipe = Recipe.makeRecipe())
    }
}