package com.example.nutri.domain.gateway

interface ApiGateway {

    suspend fun recieveRecipeData(param: String): String
}