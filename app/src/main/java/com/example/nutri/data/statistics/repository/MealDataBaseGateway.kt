package com.example.nutri.data.statistics.repository

import com.example.nutri.domain.statistics.Meal
import io.reactivex.rxjava3.core.Flowable

interface MealDataBaseGateway{

    suspend fun saveMeal(meal: Meal): Int

    fun getMealsList(date: String) : Flowable<List<Meal>>

    suspend fun addRecipeInMeal(meal: Meal)
}