package com.example.nutri.ui.screens.home

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.domain.statistics.MealInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val useCaseRecipe: LocalRecipesInteractor,
    private val useCaseMeal: MealInteractor,
    private val state: SavedStateHandle
) : ViewModel(){

    private val TAG = "StatisticsViewModel"

    var meals =  createEmptyMealsList().toMutableStateList()

    fun onStatisticsScreenLoaded() = viewModelScope.launch{

        Log.i(TAG, "Meals empty?: ${meals[0].recipes.isEmpty()}")
        /*val mealsFromDb = useCaseMeal.getMeals()
        if (mealsFromDb.isNotEmpty()){
            meals.addAll(mealsFromDb)
        }*/
    }

    fun addRecipeToMeal(id: String)
    = CoroutineScope(Dispatchers.Main).launch{

        val recipe = useCaseRecipe.getCommonRecipe(id)

        Log.i(TAG, "Recipe name: ${recipe.name}")

        meals[0].recipes.add(recipe)
    }

    private fun createEmptyMealsList()
    = listOf(Meal("Breakfast", mutableListOf(), Date()),
        Meal("Lunch", mutableListOf(), Date()),
        Meal("Dinner",mutableListOf(), Date())
    )
}