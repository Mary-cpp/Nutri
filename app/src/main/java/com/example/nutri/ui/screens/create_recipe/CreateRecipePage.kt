package com.example.nutri.ui.screens.create_recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nutri.R
import com.example.nutri.ui.screens.common.NutrientsList
import com.example.nutri.ui.screens.common.TopBarWithIcon
import com.example.nutri.ui.screens.create_recipe.composables.EmptyIngredients
import com.example.nutri.ui.screens.create_recipe.composables.IngredientsBottomSheet
import com.example.nutri.ui.screens.create_recipe.composables.IngredientsToEdit
import com.example.nutri.ui.screens.home.composables.FAB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateRecipePage(
    vm: CreateRecipeViewModel = hiltViewModel()) {

    val bottomSheetState
    = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { bottomSheetValue -> bottomSheetValue != ModalBottomSheetValue.Expanded })

    val scope = rememberCoroutineScope()
    IngredientsBottomSheet(
        ingredientList = vm.listOfIngredients,
        bottomSheetState = bottomSheetState
    ){
        EditRecipeScreenContent(vm, scope, bottomSheetState, getBack = vm::navigateBack)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditRecipeScreenContent(
    vm: CreateRecipeViewModel,
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    getBack: () ->Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWithIcon("New Recipe", getBack) },
        floatingActionButton = {
            FAB(
                onClick = {
                    scope.launch {
                        modalBottomSheetState.show()
                    } },
                color = MaterialTheme.colors.primary,
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.wrapContentSize(),
                iconRes = R.drawable.add48px,
                text = "Add ingredient"
            )
        },
        content = { RecipeEditCard( vm = vm, vm.recipeName) }
    )
}

@Composable
fun RecipeEditCard(
    vm: CreateRecipeViewModel,
    recipeName: MutableState<String>
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 24.dp, end = 16.dp),
        color = MaterialTheme.colors.secondaryVariant,
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

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 24.dp, bottom = 16.dp)
                            .size(208.dp, 64.dp),
                        singleLine = false,
                        value = recipeName.value,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.White,
                            disabledBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White
                        ),
                        onValueChange = { recipeName.value = it },
                        label = { Text(text = "Title", color = Color.White) })

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
                if (vm.listOfIngredients.isEmpty()) { EmptyIngredients() }
                else { IngredientsToEdit(vm = vm) }

                vm.recipe.value.totalNutrients?.let { nutrients ->
                    NutrientsList(nutrients = nutrients)
                }
            }
        }
    )
}