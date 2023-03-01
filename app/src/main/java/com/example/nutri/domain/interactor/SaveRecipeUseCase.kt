package com.example.nutri.domain.interactor

import com.example.nutri.data.DataBaseGatewayImpl
import com.example.nutri.domain.model.Recipe

class SaveRecipeUseCase (
    private val db: DataBaseGatewayImpl
    ): SaveRecipeInteractor {

    override suspend fun saveRecipe(recipe: Recipe){
        db.saveToLocal(recipe)
    }
}