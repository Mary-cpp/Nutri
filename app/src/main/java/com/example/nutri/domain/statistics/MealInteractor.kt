package com.example.nutri.domain.statistics

interface MealInteractor {

    suspend fun saveMeal(meal: Meal) : Int
}