package com.example.nutri.ui.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.MyNavController
import com.example.nutri.core.NavigationViewModel
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
    navControllerProvider: MyNavController
) : NavigationViewModel(navControllerProvider){

    private val tag = "SearchViewModel"

    val selectedRecipeId = mutableStateOf("")

    val foundRecipes : MutableState<List<Recipe>> = mutableStateOf(listOf())

    fun getRecipes(name: String) = viewModelScope.launch{

        Log.d(tag, "getRecipes     START")

        var listOfRecipes: List<Recipe>? = emptyList()

        if(name!=""){
            listOfRecipes = useCase.getRecipesLike(name)
        }

        listOfRecipes?.let{
            foundRecipes.value = it
        }

        Log.d(tag, "getRecipes     END")
    }

    fun addRecipeToMeal(id: String, mealName: String)
            = CoroutineScope(Dispatchers.Main).launch{

        val tag = "addRecipeToMeal"
        val recipe = useCase.getCommonRecipe(id)

        Log.i(tag, "Recipe name: ${recipe.name}")

        val meal = Meal(mealName, mutableListOf(recipe), dateFormat.format(Date()))

        useCaseMeal.addMeal(meal)
    }


}