package com.example.nutri.ui.screens.edit_recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nutri.R
import com.example.nutri.ui.screens.common.TopBarWithIcon
import com.example.nutri.ui.screens.create_recipe.composables.IngredientsBottomSheet
import com.example.nutri.ui.screens.edit_recipe.composables.EditRecipeCard
import com.example.nutri.ui.screens.home.composables.FAB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditRecipePage(
    vm : EditRecipeViewModel = hiltViewModel()
){
    val bottomSheetState
    = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { bottomSheetValue -> bottomSheetValue != ModalBottomSheetValue.Expanded }
    )

    val scope = rememberCoroutineScope()
    IngredientsBottomSheet(
        bottomSheetState = bottomSheetState,
        ingredientList = vm.ingredientList
    ){
        EditRecipePageContent(
            scope = scope,
            vm = vm,
            getBack = vm::navigateBack,
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
    getBack: () -> Unit,
    modalBottomSheetState: ModalBottomSheetState,
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWithIcon(LocalContext.current.getString(R.string.edit_recipe), getBack) },
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
                text = LocalContext.current.resources.getString(R.string.add_ingredient)
            ) },
        content = {
            if (!vm.isLoading.value){
                EditRecipeCard(vm = vm)
            }
            else{
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ){ CircularProgressIndicator() }
            }
        }
    )
}