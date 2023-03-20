package com.example.nutri.domain.recipes.interactor

import com.example.nutri.data.repository.DataBaseGatewayImpl
import com.example.nutri.domain.recipes.model.Recipe
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

    override suspend fun getCommonRecipe(recipeId: Int): Recipe {
        return db.getRecipe(recipeId = recipeId)
    }

}