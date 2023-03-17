package com.example.nutri.domain.interactor

import com.example.nutri.data.entity.RecipeEntityCommon
import com.example.nutri.data.repository.DataBaseGatewayImpl

import com.example.nutri.domain.model.Recipe
import javax.inject.Inject

class LocalRecipeUseCase @Inject constructor(
    private val db: DataBaseGatewayImpl
    ): LocalRecipesInteractor {

    override suspend fun saveRecipe(recipe: Recipe, recipeName: String): Int {
        return db.saveToLocal(recipe, recipeName)
    }

    override suspend fun receiveRecipes() : List<Recipe>{
        return db.getLocalRecipesList()
    }

    override suspend fun getCommonRecipe(recipeId: Int): RecipeEntityCommon {
        return db.getRecipe(recipeId = recipeId)
    }

}