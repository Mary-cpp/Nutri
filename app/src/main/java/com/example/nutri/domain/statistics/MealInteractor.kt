package com.example.nutri.domain.statistics

interface MealInteractor {

    suspend fun saveMeal(meal: Meal) : Int

    suspend fun getMeals(date: String? = null) : List<Meal>

    suspend fun saveMealsList(meals: List<Meal>)

    suspend fun addMeal(meal: Meal)
}