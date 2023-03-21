package com.example.nutri.data.repository

import com.example.nutri.data.bmi.database.UserDatabase
import com.example.nutri.domain.bmi.model.User

class UserDatabaseGatewayImpl(
    val database: UserDatabase
) : UserDataBaseGateway {
    override suspend fun saveToLocal(user: User): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: Int): User {
        TODO("Not yet implemented")
    }
}