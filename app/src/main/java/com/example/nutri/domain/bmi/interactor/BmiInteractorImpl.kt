package com.example.nutri.domain.bmi.interactor

import com.example.nutri.data.repository.DataBaseGateway
import com.example.nutri.domain.model.User

class BmiInteractorImpl(
    val counter: CountBMI,
    val saver: DataBaseGateway
) : BmiInteractor {
    override fun countBMI(user: User) {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}