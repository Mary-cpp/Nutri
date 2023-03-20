package com.example.nutri.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.recipes.interactor.LocalRecipeUseCase
import com.example.nutri.domain.recipes.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private var useCase: LocalRecipeUseCase
) : ViewModel(){

    val recipeId : MutableState<Int> = mutableStateOf(0)
    val recipe: MutableState<Recipe> = mutableStateOf(Recipe())

    fun onRecipeScreenLoading() = viewModelScope.launch{

        recipe.value = useCase.getCommonRecipe(recipeId.value)
    }
}