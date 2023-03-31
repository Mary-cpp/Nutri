package com.example.nutri.ui.screens.create_recipe

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutri.R
import com.example.nutri.domain.recipes.model.Ingredient
import com.example.nutri.ui.screens.RecipeBottomSheetContent
import com.example.nutri.ui.screens.common.TopBarWithIcon
import com.example.nutri.ui.screens.create_recipe.composables.EmptyIngredients
import com.example.nutri.ui.screens.create_recipe.composables.IngredientFAB
import com.example.nutri.ui.screens.create_recipe.composables.IngredientsToEdit
import com.example.nutri.ui.theme.NutriShape
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateRecipePage(
    vm: CreateRecipeViewModel,
    navController: NavController) {

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = {
                it != ModalBottomSheetValue.Expanded
            })

    val scope = rememberCoroutineScope()

    IngredientsBottomSheet(
        ingredientList = vm.listOfIngredients,
        bottomSheetState = bottomSheetState
    ) {
        EditRecipeScreenContent(vm, scope, bottomSheetState, navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IngredientsBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    ingredientList: SnapshotStateList<Ingredient>,
    screenContent: @Composable () -> Unit,
){

    val ingredientName: MutableState<String> = remember { mutableStateOf("") }
    val ingredientAmount: MutableState<Int> = remember { mutableStateOf(0) }
    val ingredientUnits: MutableState<String> = remember { mutableStateOf("g") }

    if (bottomSheetState.currentValue != ModalBottomSheetValue.Hidden){
        DisposableEffect(Unit) {
            onDispose {

                if(ingredientName.value.isNotEmpty() || ingredientAmount.value != 0){
                    val ingredient = Ingredient(ingredientName = ingredientName.value.trim(),
                        ingredientAmount = ingredientAmount.value,
                        ingredientUnits = ingredientUnits.value)

                    ingredientList.add(
                        ingredient
                    )

                    ingredientName.value = ""; ingredientAmount.value = 0

                    Log.d("BOTTOM SHEET", ingredient.toString())
                }
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
        sheetShape = NutriShape.mealsListCornerShape,
        sheetElevation = 8.dp,
    ) {
        screenContent()
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

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWithIcon("Edit", navController) },
        floatingActionButton = { IngredientFAB(scope, modalBottomSheetState) },
        content = {
            RecipeEditCard( vm = vm)
        })
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

                    TextField(modifier = Modifier
                        .padding(start = 24.dp, bottom = 16.dp)
                        .size(208.dp, 64.dp),
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