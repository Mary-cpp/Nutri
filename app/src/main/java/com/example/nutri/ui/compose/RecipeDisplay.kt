package com.example.nutri.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.model.Recipe
import com.example.nutri.ui.theme.NutriTheme


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
        RecipeDisplay(recipe = it)
    }
}

@Preview
@Composable
fun RecipePreview(){
    NutriTheme {
        RecipeDisplay(recipe = Recipe.makeRecipe())
    }
}