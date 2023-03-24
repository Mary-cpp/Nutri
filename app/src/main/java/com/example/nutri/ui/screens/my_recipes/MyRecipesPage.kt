package com.example.nutri.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.ui.screens.common.SearchField
import com.example.nutri.ui.screens.common.TopBar
import com.example.nutri.ui.screens.my_recipes.MyRecipesViewModel
import com.example.nutri.ui.screens.my_recipes.composables.RecipeFAB
import com.example.nutri.ui.screens.my_recipes.composables.RecipesList
import com.example.nutri.ui.screens.my_recipes.composables.SortAndFilter
import com.example.nutri.ui.theme.NutriTheme

const val TAG = "MyRecipesPage"
@Composable
fun MyRecipesPage(
    vm: MyRecipesViewModel = hiltViewModel(),
    navController: NavController
){

    val searchParameter = remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {  TopBar("MyRecipes") },
        floatingActionButton = { RecipeFAB(navController) })
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
                    
                    SearchField(searchParameter = searchParameter)

                    SortAndFilter()

                    RecipesList(listOfRecipes = vm.recipeList.value, navController)

                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "Keep exploring :)",
                        color = MaterialTheme.colors.secondaryVariant)
                }
            }
        }
    }

@Preview
@Composable
fun MyRecipesPagePreview(){
    NutriTheme {
        MyRecipesPage(
            vm = MyRecipesViewModel(hiltViewModel()),
            rememberNavController()
        )
    }
}