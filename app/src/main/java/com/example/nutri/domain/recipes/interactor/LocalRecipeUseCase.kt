package com.example.nutri.domain.recipes.interactor

import com.example.nutri.data.recipe.local.repository.DataBaseGatewayImpl
import com.example.nutri.domain.recipes.model.Recipe
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

    override suspend fun getCommonRecipe(recipeId: String): Recipe {
        return db.getRecipe(recipeId = recipeId)
    }

    override suspend fun getRecipesLike(name: String): List<Recipe>? {
        return db.getRecipesWithNameLike(name)
    }

}