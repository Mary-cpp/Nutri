package com.example.nutri.data.statistics.repository

import com.example.nutri.domain.statistics.Meal

interface MealDataBaseGateway{

    suspend fun saveMeal(meal: Meal): Int

    suspend fun getMealsList() : List<Meal>
}