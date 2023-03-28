package com.example.nutri.ui.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    private val TAG = "StatisticsViewModel"

    var meals: MutableState<List<Meal>> =  mutableStateOf(createEmptyMealsList())

    init {
        onStatisticsScreenLoaded()
    }

    fun onStatisticsScreenLoaded() = viewModelScope.launch {

        var hasMeals = false
        meals.value.forEach {
            if (it.recipes.isNotEmpty())
                hasMeals = true

        }
        if (!hasMeals) meals.value = createEmptyMealsList().toMutableStateList()

        Log.i(TAG, "Meals empty?: ${meals.value[0].recipes.isEmpty()}")

        var mealsFromDb : List<Meal> = listOf()
        try {mealsFromDb = useCaseMeal.getMeals(date = Date())}
        catch(e: Exception){ Log.e(TAG, "Can not fetch meals from db", e ) }
        finally{
            if (mealsFromDb.isNotEmpty()){
                meals.value = mealsFromDb
            }
        }
    }

    private fun createEmptyMealsList()
    = listOf(Meal("Breakfast", mutableListOf(), Date()),
        Meal("Lunch", mutableListOf(), Date()),
        Meal("Dinner",mutableListOf(), Date())
    )
}