package com.example.nutri.domain.statistics

import com.example.nutri.data.statistics.repository.MealDataBaseGateway
import com.example.nutri.domain.statistics.model.Meal
import io.reactivex.rxjava3.core.Flowable

class MealInteractorImpl(val db: MealDataBaseGateway) : MealInteractor
{
    override suspend fun saveMeal(meal: Meal): Int = db.saveMeal(meal)
    override suspend fun addMeal(meal: Meal) = db.addRecipeInMeal(meal)
    override fun getMeals(date: String): Flowable<List<Meal>> = db.getMealsList(date)
}