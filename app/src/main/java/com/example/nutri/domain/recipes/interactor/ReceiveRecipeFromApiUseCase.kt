package com.example.nutri.domain.recipes.interactor


import com.example.nutri.core.ResultState
import com.example.nutri.domain.recipes.ApiGateway
import com.example.nutri.domain.recipes.model.Recipe
import javax.inject.Inject

class ReceiveRecipeFromApiUseCase @Inject constructor(
    private val api: ApiGateway
    ) : RecipeInteractor {

    override suspend fun retrieveRecipe(param: String): ResultState<Recipe> {
        return api.receiveRecipeData(param)
    }
}