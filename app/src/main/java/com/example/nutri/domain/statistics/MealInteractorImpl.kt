package com.example.nutri.domain.statistics

import com.example.nutri.data.statistics.repository.MealDataBaseGateway

class MealInteractorImpl(
    val db: MealDataBaseGateway
) : MealInteractor {


    override suspend fun saveMeal(meal: Meal): Int {
        return db.saveMeal(meal)
    }
}