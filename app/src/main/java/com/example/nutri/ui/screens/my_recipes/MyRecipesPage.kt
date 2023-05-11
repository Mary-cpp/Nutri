package com.example.nutri.ui.screens.my_recipes

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.ui.screens.common.SearchFieldTest
import com.example.nutri.ui.screens.common.TopBar
import com.example.nutri.ui.screens.home.composables.FAB
import com.example.nutri.ui.screens.my_recipes.composables.RecipesList
import com.example.nutri.ui.screens.my_recipes.composables.SortAndFilter

const val TAG = "MyRecipesPage"
@Composable
fun MyRecipesPage(
    vm: RecipesViewModel
) {
    val listOfRecipes by remember { vm.recipeList }
    val isLoading by remember { vm.isDataLoading }

    Log.w(TAG, "$TAG loaded")
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar("My recipes") },
        floatingActionButton = {
            FAB(
                onClick = vm::navigateToNewRecipe,
                color = MaterialTheme.colors.primary,
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.wrapContentSize(),
                iconRes = R.drawable.add48px,
                text = "New Recipe"
            )
        })
    { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    PaddingValues(
                        start = 0.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = paddingValues.calculateBottomPadding()
                    )
                ),
            color = MaterialTheme.colors.background
        ) {

            Column(
                Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchFieldTest()
                SortAndFilter(vm)
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) { CircularProgressIndicator() }
                } else {
                    RecipesList(listOfRecipes = listOfRecipes, vm::navigateToRecipe)
                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "Keep exploring :)",
                        color = MaterialTheme.colors.secondaryVariant
                    )
                }
            }
        }
    }
}
