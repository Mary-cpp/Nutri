package com.example.nutri.domain.bmi.interactor

import com.example.nutri.core.ResultState
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User
import com.example.nutri.domain.statistics.model.Meal
import kotlinx.coroutines.flow.Flow

interface BmiInteractor {

    fun countBMI(user: User) : DietPlan

    fun countNutritionData(list: List<Meal>) : DietPlan

    suspend fun saveUser(user: User)

    suspend fun getCurrentUser() : Flow<ResultState<User>>
}