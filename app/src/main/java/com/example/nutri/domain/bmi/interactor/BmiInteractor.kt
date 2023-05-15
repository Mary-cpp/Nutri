package com.example.nutri.domain.bmi.interactor

import com.example.nutri.core.ResultState
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User
import kotlinx.coroutines.flow.Flow

interface BmiInteractor {

    fun countBMI(user: User) : DietPlan

    suspend fun saveUser(user: User)

    suspend fun getCurrentUser() : Flow<ResultState<User>>
}