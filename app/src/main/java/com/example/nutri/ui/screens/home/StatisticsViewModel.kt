package com.example.nutri.ui.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.bmi.interactor.BmiInteractor
import com.example.nutri.domain.bmi.model.User
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.domain.statistics.MealInteractor
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

val dateFormat= SimpleDateFormat("yyyy-MM-dd", Locale.US)
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val useCaseMeal: MealInteractor,
    private val useCaseBmi: BmiInteractor,
    navControllerProvider : NavControllerHolder
) : NavigationViewModel(navControllerProvider), DefaultLifecycleObserver{

    private val TAG = "StatisticsViewModel"

    val myCalories: MutableState<Int?> = mutableStateOf(0)
    val statisticsCardColor: MutableState<Color> = mutableStateOf(com.example.nutri.ui.theme.Tertiary)
    var user: MutableState<User?> = mutableStateOf(null)
    var meals: MutableState<List<Meal>> =  mutableStateOf(createEmptyMealsList())

    override fun onResume(owner: LifecycleOwner) {
        onStatisticsScreenLoaded()
    }

    private fun onStatisticsScreenLoaded() = viewModelScope.launch {

        var mealsFromDb : List<Meal> = listOf()

        try {mealsFromDb = useCaseMeal.getMeals(date = dateFormat.format(Date()))}
        catch(e: Exception){ Log.e(TAG, "Can not fetch meals from db", e ) }
        finally{ if (mealsFromDb.isNotEmpty()) meals.value = mealsFromDb }

        try{ user.value = getUserPlan().await() }
        catch(e: Exception) { Log.w(TAG, "Error fetching User DietPlan", e)}
        finally{ countCalories() }
    }

    private fun getUserPlan()
    = viewModelScope.async{
       useCaseBmi.getCurrentUser()
    }

    private fun countCalories() {

        var todayCalories = 0
        meals.value.forEach{ meal->
            if(meal.recipes.isNotEmpty()){
                meal.recipes.forEach {
                    todayCalories += it.calories?.toInt() ?: 0
                }
            }
        }
        user.value?.plan?.let {

            Log.i(TAG, "Changing card color")

            if (todayCalories >= it.kcal){
                statisticsCardColor.value = com.example.nutri.ui.theme.Error
            }
            else statisticsCardColor.value = com.example.nutri.ui.theme.PrimaryVariant
        }
        Log.i(TAG, "Today's calories $todayCalories")
        myCalories.value = todayCalories
    }

    private fun createEmptyMealsList()
    = listOf(Meal("Meals", mutableListOf(), dateFormat.format(Date()))
    )
}