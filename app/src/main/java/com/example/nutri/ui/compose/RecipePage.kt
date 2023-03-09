package com.example.nutri.ui.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutri.R
import com.example.nutri.domain.model.Recipe
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun RecipePage(){

    var recipe = Recipe.makeRecipe()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { RecipeTopBar(topBarText = recipe.name!!)},
        floatingActionButton = {FAB()},
        content = {
            Surface(Modifier.fillMaxSize().padding(it),
                color = MaterialTheme.colors.background) {
                Column {

                    ReecipeCard(recipe)
                }
            }
        })

}

@Composable

fun RecipeTopBar(topBarText: String){
    TopAppBar(title = { Text(text = topBarText, color = Color.Black)},
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        navigationIcon = {IconButton(onClick = { /*TODO("Navigate back to list")*/ },
            modifier = Modifier.padding(end = 6.dp)) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back48px),
                contentDescription = "BackToListOfRecipes",
                modifier = Modifier.size(40.dp),
                tint = Color.Black)
        }})
}


@Composable
fun ReecipeCard(recipe: Recipe){
    Surface( modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, top = 24.dp, end = 16.dp),
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
    content = {
        Column (Modifier.padding(24.dp)) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${recipe.calories.toString()} Kcal",
                    modifier = Modifier.padding(bottom = 18.dp),
                    fontSize = 24.sp)


                IconButton(onClick = { /*TODO ("Edit Recipe")*/ },
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .background(Color.Transparent)) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.edit_square48px),
                        contentDescription = "EditRecipe")

                }
            }

            Text(text = "Total weight: ${recipe.totalWeight.toString()}",
                modifier = Modifier.padding(bottom = 18.dp),
                fontSize = 16.sp)

            Labels(MaterialTheme.colors.secondary, 16, recipe.healthLabels!!)

            if (recipe.cautions != null){
                Labels(MaterialTheme.colors.secondaryVariant, 6, recipe.cautions)
            }

            Ingredients()
    }} )
}


@Composable
fun Labels(
    backgroundColor: Color,
    cornerRadius: Int,
    labels : List<String>
){
    Row (
        Modifier
            .padding(bottom = 8.dp)
            .horizontalScroll(ScrollState(0))){
        labels.forEach {

            Card(modifier = Modifier.padding(end = 8.dp),
            backgroundColor = backgroundColor,
            elevation = 6.dp,
            shape = RoundedCornerShape(cornerRadius.dp)
            ){
                Text(text = it, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp))
            }
        }
    }
}

@Composable
fun Ingredients(){

    Surface(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(top = 10.dp),
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp) {

        Column(Modifier.padding(24.dp)) {

            Text(text = "Ingredients",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
            fontSize = 24.sp)

            Ingredient()
        }
    }
}

@Composable
fun Ingredient(){
    Card(modifier = Modifier.fillMaxWidth(),
    backgroundColor = MaterialTheme.colors.primary,
    shape = RoundedCornerShape(8.dp),
        content = {

            Column{
                Text(text = "Ingredient",
                    modifier = Modifier.padding(start = 16.dp, bottom = 10.dp, top = 16.dp))

                Text(text = "Amount",
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp))
            }

        }
    )
}


@Preview
@Composable
fun RecipePagePreview(){
    NutriTheme {
        RecipePage()
    }
}