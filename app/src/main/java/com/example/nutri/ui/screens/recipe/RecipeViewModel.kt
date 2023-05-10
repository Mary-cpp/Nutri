package com.example.nutri.ui.screens.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.ResultState
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
    val isLoading: MutableState<Boolean> by lazy { mutableStateOf(true) }

    val tag = "RecipeViewModel"

    override fun onResume(owner: LifecycleOwner) {
        onRecipeScreenLoading(id)
    }

    private fun onRecipeScreenLoading(id: String) = viewModelScope.launch{
        Log.i(tag, "onRecipeScreenLoading START")
        try{
            useCase.getCommonRecipe(id).collect{ result ->
                if (result is ResultState.Success){
                    recipe.value = result.value
                    isLoading.value = false
                }
            }
        } catch (e: Throwable) {Log.e(tag, "Caught ${e.stackTrace}")}

        Log.i(tag, "onRecipeScreenLoading END")
    }
}