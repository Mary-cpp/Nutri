package com.example.nutri.domain.recipes.interactor

import com.example.nutri.data.recipe.local.repository.DataBaseGateway
import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRecipeUseCase @Inject constructor(
    private val db: DataBaseGateway
    ): LocalRecipesInteractor {

    override suspend fun saveRecipe(recipe: Recipe, recipeName: String): String {
        return db.saveToLocal(recipe, recipeName)
    }

    override suspend fun receiveRecipes() : List<Recipe>{
        return db.getLocalRecipesList()
    }

    override suspend fun getCommonRecipe(recipeId: String): Flow<Recipe> {
        return db.getRecipe(recipeId = recipeId)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        return db.deleteRecipe(recipe)
    }

    override suspend fun getRecipesLike(name: String): List<Recipe>? {
        return db.getRecipesWithNameLike(name)
    }

}