package com.example.nutri.domain.statistics

import com.example.nutri.data.statistics.repository.MealDataBaseGateway
import java.util.*

class MealInteractorImpl(
    val db: MealDataBaseGateway
) : MealInteractor {


    override suspend fun saveMeal(meal: Meal): Int {
        return db.saveMeal(meal)
    }

    override suspend fun addMeal(meal: Meal){
        return db.addRecipeInMeal(meal)
    }

    override suspend fun getMeals(date: Date?): List<Meal> {
        return db.getMealsList(date)
    }

    override suspend fun saveMealsList(meals: List<Meal>){
        return db.saveMealList(meals)
    }
}