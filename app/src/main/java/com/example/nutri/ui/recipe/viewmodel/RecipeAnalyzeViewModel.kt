package com.example.nutri.ui.recipe.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.RecipeUseCaseImp
import kotlinx.coroutines.launch

class RecipeAnalyzeViewModel (
    var useCase: RecipeUseCaseImp = RecipeUseCaseImp()
)
    : ViewModel() {

    private var _recipe: String = "Hello"
    val recipe : MutableState<String> = mutableStateOf(_recipe)
    val ingr : MutableState<String> = mutableStateOf("")


    init {
        useCase = RecipeUseCaseImp()
        Log.d("VIEW_MODEL", "START")
        start()


    }

    private fun start() = viewModelScope.launch{
        Log.d("VIEW_MODEL", "START")
        _recipe = useCase.getRecipe("apple 100g")
        Log.d("USE CASE", _recipe)
        recipe.value = _recipe
        Log.d("VIEW_MODEL", "END")
    }
}