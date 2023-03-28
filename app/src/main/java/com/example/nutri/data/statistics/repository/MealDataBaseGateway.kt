package com.example.nutri.data.statistics.repository

import com.example.nutri.domain.statistics.Meal
import java.util.*

interface MealDataBaseGateway{

    suspend fun saveMeal(meal: Meal): Int

    suspend fun getMealsList(date: Date?) : List<Meal>

    suspend fun addRecipeInMeal(meal: Meal)

    suspend fun saveMealList(list: List<Meal>)
}