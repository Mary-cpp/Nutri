package com.example.nutri.domain.interactor


import com.example.nutri.domain.gateway.ApiGateway
import com.example.nutri.domain.gateway.DataBaseGateway
import com.example.nutri.domain.model.Recipe

class RecipeInteractorImpl (
    private val api: ApiGateway,
    private val db: DataBaseGateway
    ) : RecipeInteractor {

    override suspend fun retrieveAnalysis(param: String): Recipe {
        return api.receiveRecipeData(param)
    }

    override suspend fun saveRecipe(recipe: Recipe, recipeName: String): String {
        return db.saveToLocal(recipe, recipeName)
    }

    override suspend fun receiveRecipes() : List<Recipe>{
        return db.getLocalRecipesList()
    }
}