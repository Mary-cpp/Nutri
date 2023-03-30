package com.example.nutri.domain.bmi.interactor

import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User

interface BmiInteractor {

    fun countBMI(user: User) : DietPlan

    suspend fun saveUser(user: User)

    suspend fun getCurrentUser() : User?
}