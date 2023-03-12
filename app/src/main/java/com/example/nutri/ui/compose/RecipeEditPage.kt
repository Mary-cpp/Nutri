package com.example.nutri.ui.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.R
import com.example.nutri.data.dto.Characteristics
import com.example.nutri.domain.model.Recipe
import com.example.nutri.ui.theme.NutriTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeEditPage(navController: NavController) {

    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = {
                it != ModalBottomSheetValue.Expanded
            })

    val ingredientName: MutableState<String> = remember { mutableStateOf("") }
    val ingredientAmount: MutableState<Int> = remember { mutableStateOf(0) }
    val ingredientUnits: MutableState<String> = remember { mutableStateOf("g") }

    ModalBottomSheetLayout(
        sheetContent = {
            RecipeBottomSheetContent(
                ingredientName = ingredientName,
                ingredientAmount = ingredientAmount,
                ingredientUnits = ingredientUnits)
        },
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetElevation = 8.dp,
    ) {
        EditRecipeScreenContent(scope, bottomSheetState, navController)
    }
}

@Composable
fun RecipeBottomSheetContent(
    ingredientName: MutableState<String>,
    ingredientAmount: MutableState<Int>,
    ingredientUnits: MutableState<String>
) {

    val isExpanded = remember { mutableStateOf(false) }

    Surface(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Add new ingredient",
                modifier = Modifier.padding(16.dp),
                fontSize = MaterialTheme.typography.h5.fontSize)

            OutlinedTextField(modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp),
                value = ingredientName.value,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                onValueChange = { ingredientName.value = it },
                label = { Text("Ingredient name") })

            Row(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically){
                OutlinedTextField(modifier = Modifier
                    .padding(end = 8.dp)
                    .size(width = 150.dp, height = 64.dp),
                    value = ingredientAmount.value.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                    onValueChange = { ingredientUnits.value = it },
                    label = { Text("Amount") })

                Box (modifier = Modifier.padding(top = 8.dp),
                    contentAlignment = Alignment.Center) {
                    Button(onClick = { isExpanded.value = true },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(48.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = ingredientUnits.value, fontSize = MaterialTheme.typography.caption.fontSize)
                    }

                    DropdownMenu(expanded = isExpanded.value,
                        onDismissRequest = { isExpanded.value = false }) {
                        DropdownMenuItem(onClick = { ingredientUnits.value = "g" }) {Text(text = "g") }
                        DropdownMenuItem(onClick = { ingredientUnits.value = "ml"  }) {Text(text = "ml")}
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditRecipeScreenContent(scope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState, navController: NavController) {

    val recipe = Recipe.makeRecipe()

    val recipeName: MutableState<String> = remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar("Edit", navController) },
        floatingActionButton = { IngredientFAB(scope, modalBottomSheetState) },
        content = {
            RecipeEditCard(recipe = recipe, recipeName)
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IngredientFAB(scope: CoroutineScope, bottomSheetState: ModalBottomSheetState) {
    FloatingActionButton(
        onClick = {
            scope.launch {
                bottomSheetState.show()
            }
        },
        modifier = Modifier.size(64.dp),

        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(4.dp)
    ) {

        Icon(
            ImageVector.vectorResource(id = R.drawable.add48px),
            contentDescription = "AddFAB",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }
}

@Composable
fun RecipeEditCard(recipe: Recipe, recipeName: MutableState<String>) {

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, top = 24.dp, end = 16.dp),
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        content = {
            Column(Modifier.padding(24.dp)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    TextField(modifier = Modifier
                        .padding(start = 24.dp, bottom = 16.dp)
                        .size(208.dp, 56.dp),
                        value = recipeName.value,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                        onValueChange = { recipeName.value = it },
                        label = { Text("Title") })

                    Column{
                        IconButton(
                            onClick = { /*TODO ("Save Recipe")*/ },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .background(Color.Transparent)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.save48px),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "EditRecipe"
                            )
                        }
                    }
                }

                IngredientsToEdit(recipe.ingredients?.get(0)?.parsed!!)
            }
        })
}

@Composable
fun IngredientsToEdit(ingredients : List<Characteristics>) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 10.dp),
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp
    ) {

        Row {

            Column(Modifier.padding(24.dp)) {

                Text(
                    text = "Ingredients",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )

                LazyColumn{
                    items(items = ingredients){ ingredient ->
                        IngredientEditCard(ingredient)
                    }
                }
            }
        }
    }
}

@Composable
fun IngredientEditCard(ingredient: Characteristics) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 4.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(8.dp),
        content = {

            Row (horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
                Column {
                    Text(
                        text = ingredient.foodMatch,
                        modifier = Modifier.padding(start = 16.dp, bottom = 10.dp, top = 16.dp)
                    )

                    Row {
                        Text(
                            text = ingredient.quantity.toString(),
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                        )

                        Text (
                            text = ingredient.measure,
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                        )
                    }
                }

                Column (Modifier.padding(end = 16.dp)){
                    IconButton(
                        onClick = { /*TODO ("Remove ingredient")*/ },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .background(Color.Transparent)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.remove48px),
                            tint = Color.White,
                            modifier = Modifier.size(32.dp),
                            contentDescription = "EditRecipe"
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun RecipeEditPagePreview(){
    NutriTheme {
        RecipeEditPage(rememberNavController())
    }
}