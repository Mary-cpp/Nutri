package com.example.nutri.domain.statistics

import com.example.nutri.data.statistics.repository.MealDataBaseGateway

class MealInteractorImpl(val db: MealDataBaseGateway) : MealInteractor
{
    override suspend fun saveMeal(meal: Meal): Int = db.saveMeal(meal)
    override suspend fun addMeal(meal: Meal) = db.addRecipeInMeal(meal)
    override suspend fun getMeals(date: String?): List<Meal> = db.getMealsList(date)
    override suspend fun saveMealsList(meals: List<Meal>) = db.saveMealList(meals)
}