package com.example.nutri.domain.bmi.interactor

import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User

interface CountBMI {

    fun invoke(user: User) : DietPlan
}