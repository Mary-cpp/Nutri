package com.example.nutri.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.R
import com.example.nutri.domain.model.Recipe
import com.example.nutri.ui.navigation.BottomNavigationBar
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme
import com.example.nutri.ui.viewmodel.MyRecipesViewModel


@Composable
fun MyRecipesPage(
    vm: MyRecipesViewModel = hiltViewModel(),
    navController: NavController
){

    var searchParameter by remember { mutableStateOf("") }


    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = { TopAppBar(title = { Text(text = "MyRecipesPage", color = Color.Black)},
        backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) },
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
                    OutlinedTextField(modifier = Modifier.size(width = 304.dp, height = 64.dp),
                        value = searchParameter,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                        onValueChange = { searchParameter = it },
                        trailingIcon = {
                            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.search48px),
                                contentDescription = "SearchIcon",
                                modifier = Modifier.size(32.dp)) },
                        label = { Text("Search for recipes") })

                    SortAndFilter()

                    RecipesList(listOfRecipes = vm.recipeList.value)

                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "Keep exploring :)",
                        color = MaterialTheme.colors.secondaryVariant)
                }
            }
        }
    }

@Composable
fun RecipeFAB(navController: NavController){
    FloatingActionButton(onClick = { navController.navigate(Screen.EditRecipe.screenRoute)},
        modifier = Modifier.size(56.dp),

        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(4.dp)) {

        Icon(ImageVector.vectorResource(id = R.drawable.add48px),
            contentDescription = "AddFAB",
            modifier = Modifier.size(24.dp),
            tint = Color.White)

    }
}


@Composable
fun SortAndFilter(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    horizontalArrangement = Arrangement.SpaceEvenly){
        Button(onClick = { /*TODO(Sort)*/ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondaryVariant),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.elevation(4.dp),
            content = {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.sort48px),
                    tint = Color.Black,
                    contentDescription = "SortIcon",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp))

                Text(text = "Sort", color = Color.Black, modifier = Modifier.padding(end = 16.dp))
            }
        )

        Button(onClick = { /*TODO(Filter)*/ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.elevation(4.dp)
        ) {

            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.filter_alt48px),
                tint = Color.Black,
                contentDescription = "SortIcon",
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp))

            Text(text = "Filter", color = Color.Black)
        }
    }
}

@Composable
fun RecipesList(listOfRecipes: List<Recipe>){
    LazyColumn{
        items(listOfRecipes){
            RecipeListItem(recipe = it)
        }
    }
}

@Composable
fun RecipeListItem(recipe: Recipe){
    Card(modifier = Modifier.fillMaxWidth(1f),
        backgroundColor = MaterialTheme.colors.primary,
        shape = NutriShape.smallRoundCornerShape
    ) {
        Column() {
            Text(text = recipe.name!!,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp, bottom = 10.dp, end = 16.dp),
                fontSize = 22.sp)

            Text(text = "Calories: ${recipe.calories}",
                modifier = Modifier.padding(start = 16.dp, bottom = 24.dp))
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

@Preview
@Composable
fun RecipeListItemPreview(){
    NutriTheme {
        RecipeListItem(recipe = Recipe.makeRecipe())
    }
}