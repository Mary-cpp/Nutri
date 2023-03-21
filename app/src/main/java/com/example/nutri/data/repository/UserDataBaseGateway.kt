package com.example.nutri.data.repository

import com.example.nutri.domain.bmi.model.User

interface UserDataBaseGateway {

    suspend fun saveToLocal(user: User): Int

    suspend fun getUser(id: Int) : User
}