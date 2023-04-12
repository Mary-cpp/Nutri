package com.example.nutri.ui.screens.recipe

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutri.R
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.screens.common.NutrientsList
import com.example.nutri.ui.screens.common.TopBarWithIcon
import com.example.nutri.ui.screens.create_recipe.composables.Ingredients
import com.example.nutri.ui.screens.create_recipe.composables.Labels
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipePage(
    vm: RecipeViewModel
) {

    Log.i("RecipePage", "Loaded")

    val recipe = vm.recipe.value

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWithIcon(topBarText = recipe.name!!, vm::navigateBack) },
        content = {
            Column(
                modifier = Modifier.scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical,
                    enabled = true)){
                RecipeCard(recipe = recipe, vm::navigateToRecipeEditor)
            }
        })
}

@Composable
fun RecipeCard(
    recipe: Recipe,
    navigateToRecipeEditor: (String) -> Unit,) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)
        .verticalScroll(rememberScrollState(), true),
        color = MaterialTheme.colors.surface,
        shape = NutriShape.largeRoundedCornerShape,
        elevation = 4.dp,
        content = {
            Column(
                Modifier
                    .padding(24.dp)
                    .height(1040.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${recipe.calories.toString()} KCal",
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                            .align(Alignment.CenterVertically),
                        fontSize = 24.sp
                    )

                    Column {
                        IconButton(
                            onClick = {
                                recipe.id?.let{ recipeId->
                                    navigateToRecipeEditor(recipeId) }
                                },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .background(Color.Transparent)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.edit_square48px),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "EditRecipe"
                            )
                        }
                    }
                }

                Text(
                    text = "Total weight: ${recipe.totalWeight.toString()}",
                    modifier = Modifier.padding(bottom = 18.dp),
                    fontSize = 16.sp
                )

                recipe.healthLabels?.let {
                    Labels(MaterialTheme.colors.secondary, NutriShape.mediumRoundedCornerShape,
                        it
                    )
                }

                if (recipe.cautions != null) {
                    Labels(MaterialTheme.colors.secondaryVariant, NutriShape.smallRoundedCornerShape, recipe.cautions)
                }
                recipe.ingredients?.get(0)?.parsed?.let { Ingredients(it) }
                
                recipe.totalNutrients?.let{ nutrients ->
                    NutrientsList(nutrients = nutrients)
                }
            }
        }
    )
}

@Composable
fun RecipeCardTest(
    recipe: Recipe) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        color = MaterialTheme.colors.surface,
        shape = NutriShape.largeRoundedCornerShape,
        elevation = 4.dp,
        content = {
            Column(Modifier.padding(24.dp)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${recipe.calories.toString()} KCal",
                        modifier = Modifier
                            .padding(bottom = 18.dp)
                            .align(Alignment.CenterVertically),
                        fontSize = 24.sp
                    )

                    Column {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .background(Color.Transparent)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.edit_square48px),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "EditRecipe"
                            )
                        }
                    }
                }

                Text(
                    text = "Total weight: ${recipe.totalWeight.toString()}",
                    modifier = Modifier.padding(bottom = 18.dp),
                    fontSize = 16.sp
                )

                recipe.healthLabels?.let {
                    Labels(MaterialTheme.colors.secondary, NutriShape.mediumRoundedCornerShape,
                        it
                    )
                }

                if (recipe.cautions != null) {
                    Labels(MaterialTheme.colors.secondaryVariant, NutriShape.smallRoundedCornerShape, recipe.cautions)
                }
                recipe.ingredients?.get(0)?.parsed?.let { Ingredients(it) }

                recipe.totalNutrients?.let{
                    NutrientsList(nutrients = it)
                }
            }
        }
    )
}


@Preview
@Composable
fun RecipePagePreview() {
    NutriTheme {
        RecipeCardTest(
            recipe = Recipe.makeRecipe())
    }
}