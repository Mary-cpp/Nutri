package com.example.nutri.data.repository

import com.example.nutri.domain.model.Recipe

interface ApiGateway {

    suspend fun receiveRecipeData(param: String): Recipe
}