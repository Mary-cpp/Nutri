package com.example.nutri.ui.recipe.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.interactor.RecipeInteractor
import com.example.nutri.domain.interactor.LocalRecipesInteractor
import com.example.nutri.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "VIEW_MODEL"

@HiltViewModel
class RecipeAnalyzeViewModel @Inject constructor (
    private var useCaseAnalyze: RecipeInteractor,
    private var useCaseSave: LocalRecipesInteractor
) : ViewModel() {

    val recipe : MutableState<Recipe> = mutableStateOf(Recipe())
    val ingredient : MutableState<String> = mutableStateOf("")
    val recipeList : MutableState<MutableList<Recipe>> = mutableStateOf(arrayListOf())

    val nameField : MutableState<String> = mutableStateOf("")

    fun onAnalyzeButtonPressed(ingredientParam: String) = viewModelScope.launch{
        Log.d(TAG, "onAnalyzeButtonPressed    START")

        if (ingredientParam.isEmpty()){
            throw IllegalArgumentException("Ingredient param is null")
        }
        else { recipe.value = useCaseAnalyze.retrieveRecipe(ingredientParam) }

        Log.d(TAG, "END")
    }

    fun onSaveButtonPressed() = viewModelScope.launch {
        Log.d(TAG, "onSaveButtonPressed     START")
        if (recipe.value.uri!!.isEmpty()){
            throw IllegalArgumentException("Nothing to save")
        }
        else {
            useCaseSave.saveRecipe(recipe.value, nameField.value)
        }
    }
}