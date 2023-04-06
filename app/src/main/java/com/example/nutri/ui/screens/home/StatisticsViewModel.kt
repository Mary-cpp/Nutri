package com.example.nutri.ui.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.*
import com.example.nutri.core.MyNavController
import com.example.nutri.core.NavigationViewModel
import com.example.nutri.domain.bmi.interactor.BmiInteractor
import com.example.nutri.domain.bmi.model.User
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.domain.statistics.MealInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

val dateFormat= SimpleDateFormat("yyyy-MM-dd", Locale.US)
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val useCaseMeal: MealInteractor,
    private val useCaseBmi: BmiInteractor,
    navControllerProvider : MyNavController
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

        var hasMeals = false
        meals.value.forEach {
            if (it.recipes.isNotEmpty())
                hasMeals = true

        }
        if (!hasMeals) meals.value = createEmptyMealsList().toMutableStateList()

        Log.i(TAG, "Meals empty?: ${meals.value[0].recipes.isEmpty()}")

        var mealsFromDb : List<Meal> = listOf()

        try {mealsFromDb = useCaseMeal.getMeals(date = dateFormat.format(Date()))}
        catch(e: Exception){ Log.e(TAG, "Can not fetch meals from db", e ) }
        finally{
            if (mealsFromDb.isNotEmpty()){
                meals.value = mealsFromDb
            }

            getUserPlan()
            countCalories()
        }
    }

    private fun getUserPlan() = viewModelScope.launch{
       user.value = useCaseBmi.getCurrentUser()
    }

    fun countCalories() {

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
    = listOf(Meal("Breakfast", mutableListOf(), dateFormat.format(Date())),
        Meal("Lunch", mutableListOf(), dateFormat.format(Date())),
        Meal("Dinner",mutableListOf(), dateFormat.format(Date()))
    )
}