package com.example.nutri.domain.bmi.interactor

import com.example.nutri.core.ResultState
import com.example.nutri.data.bmi.repository.UserDataBaseGateway
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User
import kotlinx.coroutines.flow.Flow

class BmiInteractorImpl(
    val counter: CountBMI,
    val saver: UserDataBaseGateway
) : BmiInteractor {
    override fun countBMI(user: User) : DietPlan {
        return counter.invoke(user)
    }

    override suspend fun saveUser(user: User) {
        saver.saveToLocal(user)
    }

    override suspend fun getCurrentUser(): Flow<ResultState<User>> {
        return saver.getLastUser()
    }
}