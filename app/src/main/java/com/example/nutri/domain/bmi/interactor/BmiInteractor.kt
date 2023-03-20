package com.example.nutri.domain.bmi.interactor

import com.example.nutri.domain.model.User

interface BmiInteractor {

    fun countBMI(user: User)

    fun saveUser(user:User)
}