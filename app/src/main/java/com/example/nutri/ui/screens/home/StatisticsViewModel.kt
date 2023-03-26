package com.example.nutri.ui.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.domain.statistics.MealInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val useCaseMeal: MealInteractor
) : ViewModel(){

    var meals = mutableStateListOf<Meal>()

    fun onStatisticsScreenLoaded() = viewModelScope.launch{
        meals = createEmptyMealsList().toMutableStateList()
        val mealsFromDb = useCaseMeal.getMeals()
        if (mealsFromDb.isNotEmpty()){
            meals.addAll(mealsFromDb)
        }
    }

    fun addRecipeToMeal(id: String) = viewModelScope.launch{


    }

    private fun createEmptyMealsList()
    = listOf(Meal("Breakfast", emptyList(), Date()),
        Meal("Lunch", emptyList(), Date()),
        Meal("Dinner", emptyList(), Date())
    )
}