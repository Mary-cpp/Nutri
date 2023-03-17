package com.example.nutri.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nutri.R
import com.example.nutri.domain.model.Ingredient
import com.example.nutri.ui.viewmodel.CreateRecipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeEditPage(
    vm: CreateRecipeViewModel = hiltViewModel(),
    navController: NavController) {

    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = {
                it != ModalBottomSheetValue.Expanded
            })

    val ingredientName: MutableState<String> = remember { mutableStateOf("") }
    val ingredientAmount: MutableState<Int> = remember { mutableStateOf(0) }
    val ingredientUnits: MutableState<String> = remember { mutableStateOf("g") }

    if (bottomSheetState.currentValue != ModalBottomSheetValue.Hidden){
        DisposableEffect(Unit) {
            onDispose {

                val ingredient = Ingredient(ingredientName = ingredientName.value,
                    ingredientAmount = ingredientAmount.value,
                    ingredientUnits = ingredientUnits.value)

                vm.listOfIngredients.add(
                    ingredient
                )

                Log.d("BOTTOM SHEET", ingredient.toString())
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            RecipeBottomSheetContent(
                ingredientName = ingredientName,
                ingredientAmount = ingredientAmount,
                ingredientUnits = ingredientUnits
            )
        },
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetElevation = 8.dp,
    ) {
        EditRecipeScreenContent(vm, scope, bottomSheetState, navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditRecipeScreenContent(
    vm: CreateRecipeViewModel,
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    navController: NavController
) {

    val recipeName: MutableState<String> = remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar("Edit", navController) },
        floatingActionButton = { IngredientFAB(scope, modalBottomSheetState) },
        content = {
            RecipeEditCard( vm = vm)
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
        modifier = Modifier.size(56.dp),

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
fun RecipeEditCard(
    vm: CreateRecipeViewModel
){

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

                    OutlinedTextField(modifier = Modifier
                        .padding(start = 24.dp, bottom = 16.dp)
                        .size(208.dp, 56.dp),
                        value = vm.recipeName.value,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                        onValueChange = { vm.recipeName.value = it },
                        label = { Text("Title") })

                    Column{

                        IconButton(
                            onClick = { vm.onSaveButtonPressed() },
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

                        IconButton(
                            onClick = {
                                vm.onAnalyzeButtonPressed(
                                    vm.ingredientsToString(vm.listOfIngredients
                                    ))
                            },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .background(Color.Transparent)
                        ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.search48px),
                            modifier = Modifier.size(32.dp),
                            contentDescription = "EditRecipe"
                        ) }
                    }
                }

                if (vm.listOfIngredients.isEmpty()) {
                    EmptyIngredients()
                } else {
                    IngredientsToEdit(vm = vm)
                }
            }
        })
}

@Composable
fun IngredientsToEdit(vm: CreateRecipeViewModel) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 10.dp),
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp
    ) {

        Column(Modifier.padding(24.dp)) {

            Text(
                text = "Ingredients",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )

            LazyColumn {
                items(items = vm.listOfIngredients) { ingredient ->
                    IngredientEditCard(ingredient) { vm.onRemoveButtonPressed(ingredient) }
                }
            }
        }
    }
}

@Composable
fun EmptyIngredients(){
    Surface(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 10.dp),
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp
    ) {

        Column(Modifier.padding(24.dp)) {

            Text(
                text = "Ingredients",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h5.fontSize
            )

            Text(text = "No ingredients added",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }
}

@Composable
fun IngredientEditCard(ingredient: Ingredient, deleteItem: () -> Unit) {
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
                        text = ingredient.ingredientName,
                        modifier = Modifier.padding(start = 16.dp, bottom = 10.dp, top = 16.dp)
                    )

                    Row {
                        Text(
                            text = ingredient.ingredientAmount.toString(),
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                        )

                        Text (
                            text = ingredient.ingredientUnits,
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                        )
                    }
                }

                Column (Modifier.padding(end = 16.dp)){
                    IconButton(
                        onClick = { deleteItem() },
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