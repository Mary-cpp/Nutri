package com.example.nutri.domain.recipes

import com.example.nutri.core.ResultState
import com.example.nutri.domain.recipes.model.Recipe

interface ApiGateway {

    suspend fun receiveRecipeData(param: String): ResultState<Recipe>
}