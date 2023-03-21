package com.example.nutri.ui.screens.my_recipes

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
class MyRecipesViewModel @Inject constructor(
    private var useCase: LocalRecipesInteractor
) : ViewModel() {

    val recipeList : MutableState<List<Recipe>> = mutableStateOf(listOf())

    private val tag = "MyRecipesViewModel"

    init {
        getSavedRecipes()
    }

    private fun getSavedRecipes() = viewModelScope.launch{
        Log.d(tag, "MyRecipes Screen Loaded        START")

        recipeList.value = useCase.receiveRecipes()

        Log.d(tag, "MyRecipes Screen Loaded         END")
    }
}