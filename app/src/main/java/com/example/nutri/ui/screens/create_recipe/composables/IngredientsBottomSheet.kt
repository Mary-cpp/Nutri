package com.example.nutri.ui.screens.create_recipe.composables

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.recipes.model.Ingredient
import com.example.nutri.ui.screens.create_recipe.RecipeBottomSheetContent
import com.example.nutri.ui.theme.NutriShape

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IngredientsBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    ingredientList: SnapshotStateList<Ingredient>,
    screenContent: @Composable () -> Unit,
){

    val ingredientName: MutableState<String> = remember { mutableStateOf("") }
    val ingredientAmount: MutableState<String> = remember { mutableStateOf("") }
    val ingredientUnits: MutableState<String> = remember { mutableStateOf("g") }

    if (bottomSheetState.currentValue != ModalBottomSheetValue.Hidden){
        DisposableEffect(Unit) {
            onDispose {

                if(ingredientName.value.isNotEmpty() || ingredientAmount.value != "0"){
                    val ingredient = Ingredient(ingredientName = ingredientName.value.trim(),
                        ingredientAmount = ingredientAmount.value.toInt(),
                        ingredientUnits = ingredientUnits.value)

                    ingredientList.add(
                        ingredient
                    )

                    ingredientName.value = ""; ingredientAmount.value = ""

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