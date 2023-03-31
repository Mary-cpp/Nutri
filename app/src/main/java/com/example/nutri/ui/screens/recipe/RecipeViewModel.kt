package com.example.nutri.ui.screens.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private var useCase: LocalRecipesInteractor
) : ViewModel(){

    val recipe: MutableState<Recipe> = mutableStateOf(Recipe())

    val tag = "RecipeViewModel"

    fun onRecipeScreenLoading(id: String) = viewModelScope.launch{

        Log.i(tag, "onRecipeScreenLoading START")
        recipe.value = useCase.getCommonRecipe(id)

        Log.i(tag, "onRecipeScreenLoading END")
    }
}