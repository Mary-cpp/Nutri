package com.example.nutri.domain.interactor


import com.example.nutri.domain.gateway.ApiGateway
import com.example.nutri.domain.model.Recipe

class ReceiveRecipeFromApiUseCase (
    private val api: ApiGateway
    ) : RecipeInteractor {

    override suspend fun retrieveRecipe(param: String): Recipe {
        return api.receiveRecipeData(param)
    }
}