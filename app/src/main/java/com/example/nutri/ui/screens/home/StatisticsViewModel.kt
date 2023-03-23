package com.example.nutri.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.recipes.interactor.RecipeInteractor
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.domain.statistics.MealInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    val useCaseRecipe: RecipeInteractor,
    val useCaseMeal: MealInteractor
) : ViewModel(){

    val meals: MutableState<List<Meal>> = mutableStateOf(listOf())

    fun onStatisticsScreenLoaded() = viewModelScope.launch{
        meals.value = useCaseMeal.getMeals()
    }
}