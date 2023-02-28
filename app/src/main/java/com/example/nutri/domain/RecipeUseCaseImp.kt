package com.example.nutri.domain

import com.example.nutri.data.ApiGatewayImpl
import com.example.nutri.data.DataBaseGatewayImpl
import com.example.nutri.data.entity.RecipeEntity
import com.example.nutri.domain.model.Recipe

class RecipeUseCaseImp(): RecipeUseCase {

    private val api = ApiGatewayImpl()
    private val db = DataBaseGatewayImpl()

    suspend fun retrieveRecipe(param: String): Recipe {
        return api.recieveRecipeData(param)
    }

    suspend fun saveRecipe(param: RecipeEntity){
    }
}