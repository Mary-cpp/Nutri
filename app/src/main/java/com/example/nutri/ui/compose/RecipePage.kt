package com.example.nutri.ui.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

    Surface(Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background) {
        Column {

            Row (Modifier.padding(bottom = 16.dp)){

                SmallTopAppBar(title = { Text(text = recipe.name!!)},
                    colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.background),
                    navigationIcon = {IconButton(onClick = { /*TODO("Navigate back to list")*/ },
                    modifier = Modifier.padding(end = 8.dp)) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back48px),
                        contentDescription = "BackToListOfRecipes")
                }})
            }

            ReecipeCard(recipe)
        }
    }
}


@Composable
fun ReecipeCard(recipe: Recipe){
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 4.dp) {
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

            Labels(MaterialTheme.colorScheme.secondary, 16, recipe.healthLabels!!)

            if (recipe.cautions != null){
                Labels(MaterialTheme.colorScheme.tertiary, 6, recipe.cautions)
            }

            Ingredients()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            elevation = CardDefaults.elevatedCardElevation(6.dp),
            shape = RoundedCornerShape(cornerRadius.dp)
            ){
                Text(text = it, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp))
            }
        }
    }
}

@Composable
fun Ingredients(){

    Surface(
        Modifier
            .fillMaxWidth(1f)
            .padding(top = 10.dp),
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 4.dp) {

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ingredient(){
    Card(modifier = Modifier.fillMaxWidth(1f),
    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
    shape = RoundedCornerShape(6.dp)
    ) {

        Text(text = "Ingredient",
        modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp))

        Text(text = "Amount",
        modifier = Modifier.padding(start = 16.dp, bottom = 10.dp))
    }
}


@Preview
@Composable
fun RecipePagePreview(){
    NutriTheme {
        RecipePage()
    }
}