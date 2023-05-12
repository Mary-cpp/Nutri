package com.example.nutri.domain.statistics

import io.reactivex.rxjava3.core.Flowable

interface MealInteractor {

    suspend fun saveMeal(meal: Meal) : Int
    fun getMeals(date: String) : Flowable<List<Meal>>
    suspend fun addMeal(meal: Meal)
}