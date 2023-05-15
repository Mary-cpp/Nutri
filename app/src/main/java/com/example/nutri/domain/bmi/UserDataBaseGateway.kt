package com.example.nutri.domain.bmi

import com.example.nutri.core.ResultState
import com.example.nutri.domain.bmi.model.User
import kotlinx.coroutines.flow.Flow

interface UserDataBaseGateway {

    suspend fun saveToLocal(user: User): String
    suspend fun getLastUser() : Flow<ResultState<User>>
}