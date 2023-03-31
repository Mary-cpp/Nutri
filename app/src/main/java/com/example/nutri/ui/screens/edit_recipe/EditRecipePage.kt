package com.example.nutri.ui.screens.edit_recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.nutri.ui.screens.common.TopBarWithIcon
import com.example.nutri.ui.screens.create_recipe.composables.IngredientFAB
import com.example.nutri.ui.screens.create_recipe.composables.IngredientsBottomSheet
import com.example.nutri.ui.screens.edit_recipe.composables.EditRecipeCard
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditRecipePage(
    navController: NavController,
    vm : EditRecipeViewModel
){

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = {
                it != ModalBottomSheetValue.Expanded
            })

    val scope = rememberCoroutineScope()

    IngredientsBottomSheet(bottomSheetState = bottomSheetState,
        ingredientList = vm.ingredientList) {
        EditRecipePageContent(
            scope = scope,
            vm = vm,
            navController = navController,
            modalBottomSheetState = bottomSheetState
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditRecipePageContent(
    scope: CoroutineScope,
    vm: EditRecipeViewModel,
    modalBottomSheetState: ModalBottomSheetState,
    navController: NavController
){
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWithIcon("Edit", navController) },
        floatingActionButton = { IngredientFAB(scope, modalBottomSheetState) },
        content = {
            EditRecipeCard(vm = vm, navController = navController)
        }
    )
}