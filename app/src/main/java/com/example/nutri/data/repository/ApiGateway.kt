package com.example.nutri.data.repository

import com.example.nutri.domain.recipes.model.Recipe

interface ApiGateway {

    suspend fun receiveRecipeData(param: String): Recipe
}