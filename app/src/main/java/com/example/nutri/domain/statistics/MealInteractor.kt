package com.example.nutri.domain.statistics

import java.util.*

interface MealInteractor {

    suspend fun saveMeal(meal: Meal) : Int

    suspend fun getMeals(date: Date? = null) : List<Meal>

    suspend fun saveMealsList(meals: List<Meal>)

    suspend fun addMeal(meal: Meal)
}