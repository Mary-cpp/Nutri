package com.example.nutri.data.statistics.repository

import com.example.nutri.domain.statistics.model.Meal
import io.reactivex.rxjava3.core.Flowable

interface MealDataBaseGateway{

    suspend fun saveMealInfo(meal: Meal): Int

    fun getMealsListFlowByDate(date: String) : Flowable<List<Meal>>

    suspend fun addRecipeInMeal(meal: Meal)
}