package com.example.nutri.domain.gateway

import android.util.Log
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.entity.RecipeEntity
import com.example.nutri.domain.model.Recipe
import javax.inject.Inject

class DataBaseGatewayImpl @Inject constructor(
    val database: RecipeDatabase
    ) : DataBaseGateway {

    val TAG: String = "DataBaseGatewayImpl"



    override suspend fun saveToLocal(recipe: Recipe, recipeName: String): String {
        Log.d(TAG, "saveToLocal         START")

        var recipeEnt = mapToRecipeEntity(recipe)

        recipeEnt.name = recipeName

        database.recipeDAO().add(recipeEnt)

        Log.d(TAG, "saveToLocal         END")

        return recipeName
    }

    override suspend fun getLocalRecipesList() : List<Recipe> {

        Log.d(TAG, "getLocalRecipesList         START")

        val entityRecipes = database.recipeDAO().getRecipes()
        val recipes = mutableListOf<Recipe>()
        entityRecipes.forEach {
            recipes.add(mapToRecipe(it)) }

        Log.d(TAG, "getLocalRecipesList        ${recipes.size} END")

        return recipes.toList()
    }

    override suspend fun deleteSavedRecipes () : String{

        database.recipeDAO().clear()
        return "All saved recipes deleted"
    }


    private fun mapToRecipe(recipe: RecipeEntity) : Recipe {
        return Recipe(
            id = recipe.id,
            uri = recipe.url,
            calories = recipe.calories
       )
    }

    private fun mapToRecipeEntity(recipe: Recipe) : RecipeEntity {
        return RecipeEntity(
            id = null,
            name = null,
            url = recipe.uri,
            calories = recipe.calories
       )
   }
}