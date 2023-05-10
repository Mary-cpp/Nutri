package com.example.nutri.ui.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.ResultState
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationViewModel
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.domain.statistics.MealInteractor
import com.example.nutri.ui.screens.home.dateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: LocalRecipesInteractor,
    private val useCaseMeal: MealInteractor,
    navControllerProvider: NavControllerHolder
) : NavigationViewModel(navControllerProvider){

    val selectedRecipeId = mutableStateOf("")
    //val isLoading: MutableState<Boolean> by lazy { mutableStateOf(true) }
    val foundRecipes : MutableState<List<Recipe>> = mutableStateOf(listOf())

    private val tag = "SearchViewModel"

    fun getRecipes(name: String) = viewModelScope.launch{

        Log.d(tag, "getRecipes     START")
        if(name.isEmpty()) return@launch

        val listOfRecipes: List<Recipe>? = useCase.getRecipesLike(name)
        listOfRecipes?.let{ foundRecipes.value = it }

        Log.d(tag, "getRecipes     END")
    }

    fun addRecipeToMeal(id: String, mealName: String)
    = CoroutineScope(Dispatchers.Main).launch{

        val tag = "addRecipeToMeal"

        useCase.getCommonRecipe(id).collect{ recipe ->
            if (recipe is ResultState.Success){
                Log.i(tag, "Recipe name: ${recipe.value.name}")
                useCaseMeal.addMeal(
                    Meal(mealName, mutableListOf(recipe.value), dateFormat.format(Date()))
                )
                //isLoading.value = false
            }
        }
    }
}