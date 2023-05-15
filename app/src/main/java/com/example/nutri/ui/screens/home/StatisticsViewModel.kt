package com.example.nutri.ui.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.ResultState
import com.example.nutri.domain.bmi.interactor.BmiInteractor
import com.example.nutri.domain.bmi.model.User
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.domain.statistics.MealInteractor
import com.example.nutri.domain.statistics.Water
import com.example.nutri.domain.statistics.WaterUseCaseInteractor
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

val dateFormat= SimpleDateFormat("yyyy-MM-dd", Locale.US)
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val useCaseMeal: MealInteractor,
    private val useCaseBmi: BmiInteractor,
    private val useCaseWater: WaterUseCaseInteractor,
    navControllerProvider : NavControllerHolder
) : NavigationViewModel(navControllerProvider), DefaultLifecycleObserver{

    private val TAG = "StatisticsViewModel"

    val myCalories: MutableState<Int?> = mutableStateOf(0)
    val statisticsCardColor: MutableState<Color> = mutableStateOf(com.example.nutri.ui.theme.Tertiary)
    var user: MutableState<User?> = mutableStateOf(null)
    var meals: MutableState<List<Meal>> =  mutableStateOf(createEmptyMealsList())
    var water: MutableState<Water> = mutableStateOf(Water(Date(), 1))

    override fun onResume(owner: LifecycleOwner) {
        onStatisticsScreenLoaded(dateFormat.format(Date()))
    }

    override fun onPause(owner: LifecycleOwner) {
        viewModelScope.launch {
            try{
                useCaseWater.updateData(water.value)
            } catch (tr: Throwable) {Log.e(TAG, "Cannot update water info", tr)}
        }
    }

    private fun onStatisticsScreenLoaded(date: String) = viewModelScope.launch {

        useCaseMeal.getMeals(date)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Log.e(TAG, "Caught ${it.message}")
            }
            .subscribe(
                { mealsFromDb : List<Meal> ->
                    if (mealsFromDb.isEmpty()) meals.value = createEmptyMealsList()
                    else meals.value = mealsFromDb
                    Log.i(TAG, "Meals list size: ${mealsFromDb.size}")
                },
                { tr : Throwable ->
                    Log.e(TAG, "Error fetching meal data from db", tr)
                }
            )

        try {
            water.value = useCaseWater.loadData(dateFormat.parse(date) as Date)
            Log.i(TAG, "Water from db amount: ${water.value.amount}")
        }
        catch(tr: Throwable) { Log.e(TAG, "Cannot fetch water info from db", tr)}

        useCaseBmi.getCurrentUser().collect{ result ->
            when (result){
                is ResultState.Success<User> -> user.value = result.value
                is ResultState.Loading -> {}
                is ResultState.Error -> Log.e(TAG, "Error fetching user info from db", result.exception)
            }
            countCalories()
        }
    }

    fun onWaterAddButtonClicked() {
        water.value = Water(Date(), water.value.amount+1)
        Log.i(TAG, "Water added amount: ${water.value.amount}")
    }
    fun onWaterRemoveButtonClicked() {
        if (water.value.amount > 0){
            water.value = Water(Date(), water.value.amount-1)
        }
        Log.i(TAG, "Water removed amount: ${water.value.amount}")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) = viewModelScope.launch{
        Log.i(TAG, "Selected date: $day, $month, $year")
        Log.i(TAG, "Formatted Date: ${dateFormat.format(Date())}")

        onStatisticsScreenLoaded(date = dateFormat.format(dateFormat.parse("$year-$month-$day") as Date))
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