package com.example.nutri.domain

import com.example.nutri.data.ApiGatewayImpl
import com.example.nutri.data.DataBaseGatewayImpl
import com.example.nutri.data.entity.RecipeEntity

class RecipeUseCaseImp(): RecipeUseCase {

    private val api = ApiGatewayImpl()
    private val db = DataBaseGatewayImpl()

    suspend fun getRecipe(param: String): String{
        return api.recieveRecipeData(param)
    }

    suspend fun saveRecipe(param: RecipeEntity){
    }
}