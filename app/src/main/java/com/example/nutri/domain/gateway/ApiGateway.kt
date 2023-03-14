package com.example.nutri.domain.gateway

import com.example.nutri.domain.model.Recipe
import com.example.nutri.domain.model.Recipe2

interface ApiGateway {

    suspend fun receiveRecipeData(param: String): Recipe2
}