package com.example.nutri.domain.interactor

import com.example.nutri.domain.gateway.DataBaseGatewayImpl

import com.example.nutri.domain.model.Recipe
import javax.inject.Inject

class LocalRecipeUseCase @Inject constructor(
    private val db: DataBaseGatewayImpl
    ): LocalRecipesInteractor {

    override suspend fun saveRecipe(recipe: Recipe, recipeName: String): String {
        return db.saveToLocal(recipe, recipeName)
    }

    override suspend fun receiveRecipes() : List<Recipe>{
        return db.getLocalRecipesList()
    }


}