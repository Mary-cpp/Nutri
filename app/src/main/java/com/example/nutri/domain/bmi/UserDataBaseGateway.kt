package com.example.nutri.domain.bmi

import com.example.nutri.core.ResultState
import com.example.nutri.domain.bmi.model.User
import kotlinx.coroutines.flow.Flow

interface UserDataBaseGateway {

    suspend fun saveUserInfo(user: User): String
    suspend fun getLastUserInfo() : Flow<ResultState<User>>
}