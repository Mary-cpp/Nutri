package com.example.nutri.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutri.R
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.domain.gateway.ApiGatewayImpl
import com.example.nutri.domain.gateway.DataBaseGatewayImpl
import com.example.nutri.domain.interactor.LocalRecipeUseCase
import com.example.nutri.domain.interactor.ReceiveRecipeFromApiUseCase
import com.example.nutri.domain.model.Recipe
import com.example.nutri.ui.recipe.viewmodel.RecipeAnalyzeViewModel
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun MyRecipesPage(vm: RecipeAnalyzeViewModel){

    var recipe = Recipe.makeRecipe()

    var searchParameter by remember { mutableStateOf("") }

    Column {
        SmallTopAppBar(title = { Text(text = "MyRecipesPage")})

        Surface(modifier = Modifier.fillMaxSize()){

            Column(Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp)){
                OutlinedTextField(modifier = Modifier.size(width = 304.dp, height = 64.dp),
                    value = searchParameter,
                    onValueChange = { searchParameter = it },
                    trailingIcon = {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.search48px),
                            contentDescription = "SearchIcon",
                            modifier = Modifier.size(32.dp)) },
                    label = { Text("Search for recipes") })

                SortAndFilter()

                RecipesList(listOfRecipes = vm.recipeList.value)
            }
        }
    }





}


@Composable
fun SortAndFilter(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    horizontalArrangement = Arrangement.SpaceEvenly){
        Button(onClick = { /*TODO(Sort)*/ },
            colors = ButtonDefaults.elevatedButtonColors(MaterialTheme.colorScheme.tertiary),
            elevation = ButtonDefaults.buttonElevation((-4).dp)
        ) {

            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.sort48px),
                tint = Color.Black,
                contentDescription = "SortIcon",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp))

            Text(text = "Sort", color = Color.Black)
        }

        Button(onClick = { /*TODO(Filter)*/ },
            colors = ButtonDefaults.elevatedButtonColors(MaterialTheme.colorScheme.secondary),
            elevation = ButtonDefaults.buttonElevation((-4).dp)
        ) {

            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.filter_alt48px),
                tint = Color.Black,
                contentDescription = "SortIcon",
                modifier = Modifier
                    .size(24.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListItem(recipe: Recipe){
    Card(modifier = Modifier.fillMaxWidth(1f),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(6.dp)
    ) {

        Text(text = recipe.name!!,
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 10.dp),
        fontSize = 22.sp)

        Text(text = "Calories: ${recipe.calories}",
            modifier = Modifier.padding(start = 16.dp, bottom = 24.dp))
    }
}


@Preview
@Composable
fun MyRecipesPagePreview(){
    NutriTheme {
        MyRecipesPage(vm = RecipeAnalyzeViewModel(
            useCaseAnalyze = ReceiveRecipeFromApiUseCase(api = ApiGatewayImpl()),
            useCaseSave = LocalRecipeUseCase(db = DataBaseGatewayImpl(database = RecipeDatabase.getDatabase(context = LocalContext.current)))
        )
        )
    }
}