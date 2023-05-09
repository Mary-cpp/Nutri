package com.example.nutri.ui.screens.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private var useCase: LocalRecipesInteractor,
    navControllerProvider: NavControllerHolder
) : NavigationViewModel(navControllerProvider), DefaultLifecycleObserver{

    val recipe: MutableState<Recipe> = mutableStateOf(Recipe())
    var id: String = ""

    val tag = "RecipeViewModel"

    override fun onResume(owner: LifecycleOwner) {
        onRecipeScreenLoading(id)
    }

    private fun onRecipeScreenLoading(id: String) = viewModelScope.launch{
        Log.i(tag, "onRecipeScreenLoading START")
        useCase.getCommonRecipe(id).collect{
            recipe.value = it
        }

        Log.i(tag, "onRecipeScreenLoading END")
    }
}