package com.example.nutri.domain

import com.example.nutri.data.ApiGatewayImpl
import com.example.nutri.domain.model.Recipe

class ReceiveApiRecipeUseCase() : RecipeInteractor {

    private val api =  ApiGatewayImpl()

    override suspend fun retrieveRecipe(param: String): Recipe {
        return api.recieveRecipeData(param)
    }
}