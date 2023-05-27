package com.example.nutri.ui.screens.my_recipes

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.ResultState
import com.example.nutri.core.data
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationViewModel
import com.example.nutri.ui.screens.my_recipes.composables.SortAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private var useCase: LocalRecipesInteractor,
    navControllerProvider: NavControllerHolder,
) : NavigationViewModel(navControllerProvider), DefaultLifecycleObserver {

    val recipeList : MutableState<List<Recipe>> = mutableStateOf(listOf())
    val isDataLoading : MutableState<Boolean> = mutableStateOf(true)

    private var _recipeListState : MutableStateFlow<ResultState<List<Recipe>>>
    = MutableStateFlow(ResultState.Loading)
    val recipeListState : StateFlow<ResultState<List<Recipe>>> = _recipeListState

    private val tag = "MyRecipesViewModel"

    override fun onResume(owner: LifecycleOwner) {
        getSavedRecipes()
    }

    private fun getSavedRecipes() = viewModelScope.launch{
        Log.d(tag, "MyRecipes Screen Loaded        START")

        useCase.receiveRecipes().collect{ result ->
            _recipeListState.value = result
            when(recipeListState.value){
                is ResultState.Success -> {
                    recipeList.value = recipeListState.value.data
                    isDataLoading.value = false
                }
                is ResultState.Loading -> isDataLoading.value = true
                is ResultState.Error -> {}
            }
        }
    }

    fun onSortListSelectedItemChanged(param: SortAction) {
        viewModelScope.launch {
            useCase.sortRecipesBy(
                sortAction = param,
                list = recipeList.value
            )
                .collect { sortResult -> recipeList.value = sortResult }
        }
    }
}