package com.example.nutri.ui.screens.create_recipe

import android.annotation.SuppressLint
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
import com.example.nutri.ui.screens.common.TopBarWithIcon
import com.example.nutri.ui.screens.create_recipe.composables.EmptyIngredients
import com.example.nutri.ui.screens.create_recipe.composables.IngredientFAB
import com.example.nutri.ui.screens.create_recipe.composables.IngredientsBottomSheet
import com.example.nutri.ui.screens.create_recipe.composables.IngredientsToEdit
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateRecipePage(
    vm: CreateRecipeViewModel = hiltViewModel()) {

    val bottomSheetState
    = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { bottomSheetValue -> bottomSheetValue != ModalBottomSheetValue.Expanded })

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
        topBar = { TopBarWithIcon("Edit", getBack) },
        floatingActionButton = { IngredientFAB(scope, modalBottomSheetState) },
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

                    TextField(
                        modifier = Modifier
                        .padding(start = 24.dp, bottom = 16.dp)
                        .size(208.dp, 64.dp),
                        value = recipeName.value,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                        onValueChange = { recipeName.value = it },
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
                if (vm.listOfIngredients.isEmpty()) { EmptyIngredients() }
                else { IngredientsToEdit(vm = vm) }
            }
        }
    )
}