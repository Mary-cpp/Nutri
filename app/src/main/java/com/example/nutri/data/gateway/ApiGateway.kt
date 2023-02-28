package com.example.nutri.data.gateway

import com.example.nutri.domain.model.Recipe

interface ApiGateway {

    suspend fun recieveRecipeData(param: String = "nothing"): Recipe
}