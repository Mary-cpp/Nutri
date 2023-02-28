package com.example.nutri.ui.recipe.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.ReceiveApiRecipeUseCase
import com.example.nutri.domain.model.Recipe
import kotlinx.coroutines.launch

class RecipeAnalyzeViewModel (
    private var useCase: ReceiveApiRecipeUseCase = ReceiveApiRecipeUseCase()
)
    : ViewModel() {

    val recipe : MutableState<Recipe> = mutableStateOf(Recipe())
    val ingredient : MutableState<String> = mutableStateOf("")


    init {
        useCase = ReceiveApiRecipeUseCase()
        Log.d("VIEW_MODEL", "START")

    }

    fun onAnalyzeButtonPressed(ingredientParam: String) = viewModelScope.launch{
        Log.d("VIEW_MODEL", "START")
        recipe.value = useCase.retrieveRecipe(ingredientParam)
        Log.d("VIEW_MODEL", "END")
    }
}