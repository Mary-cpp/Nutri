package com.example.nutri.ui.screens.my_recipes

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.MyNavController
import com.example.nutri.core.NavigationViewModel
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private var useCase: LocalRecipesInteractor,
    navControllerProvider: MyNavController,
) : NavigationViewModel(navControllerProvider), DefaultLifecycleObserver {

    val recipeList : MutableState<List<Recipe>> = mutableStateOf(listOf())

    private val tag = "MyRecipesViewModel"

    override fun onResume(owner: LifecycleOwner) {
        getSavedRecipes()
    }

    fun getSavedRecipes() = viewModelScope.launch{

        Log.d(tag, "MyRecipes Screen Loaded        START")

        recipeList.value = useCase.receiveRecipes()

        Log.d(tag, "MyRecipes Screen Loaded         END")
    }
}