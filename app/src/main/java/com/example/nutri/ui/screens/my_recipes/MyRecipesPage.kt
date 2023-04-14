package com.example.nutri.ui.screens.my_recipes

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.screens.common.SearchFieldTest
import com.example.nutri.ui.screens.common.TopBar
import com.example.nutri.ui.screens.my_recipes.composables.RecipeFAB
import com.example.nutri.ui.screens.my_recipes.composables.RecipesList
import com.example.nutri.ui.screens.my_recipes.composables.SortAndFilter

const val TAG = "MyRecipesPage"
@Composable
fun MyRecipesPage(
    vm: RecipesViewModel
){
    Log.w(TAG, "$TAG loaded")
        Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {  TopBar("My recipes") },
        floatingActionButton = { RecipeFAB(goToScreen = vm::navigateToNewRecipe) })
        { paddingValues ->
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(
                    PaddingValues(
                        start = 0.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = paddingValues.calculateBottomPadding()
                    )
                ),
                color = MaterialTheme.colors.background){

                Column(Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    SearchFieldTest()
                    SortAndFilter(vm)
                    RecipesList(listOfRecipes = vm.recipeList.value, vm::navigateToRecipe)
                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "Keep exploring :)",
                        color = MaterialTheme.colors.secondaryVariant)
                }
            }
        }
}
