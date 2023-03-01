package com.example.nutri.data.gateway

import com.example.nutri.domain.model.Recipe

interface ApiGateway {

    suspend fun receiveRecipeData(param: String = "nothing"): Recipe
}