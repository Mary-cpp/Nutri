package com.example.nutri.domain.gateway

import android.util.Log
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.entity.RecipeEntity
import com.example.nutri.domain.model.Recipe2
import javax.inject.Inject

class DataBaseGatewayImpl @Inject constructor(
    val database: RecipeDatabase
    ) : DataBaseGateway {

    val TAG: String = "DataBaseGatewayImpl"



    override suspend fun saveToLocal(recipe: Recipe2, recipeName: String): String {
        Log.d(TAG, "saveToLocal         START")

        var recipeEnt = mapToRecipeEntity(recipe)

        recipeEnt.name = recipeName

        database.recipeDAO().add(recipeEnt)

        Log.d(TAG, "saveToLocal         END")

        return recipeName
    }

    override suspend fun getLocalRecipesList() : List<Recipe2> {

        Log.d(TAG, "getLocalRecipesList         START")

        val entityRecipes = database.recipeDAO().getRecipes()
        val recipes = mutableListOf<Recipe2>()
        entityRecipes.forEach {
            recipes.add(mapToRecipe(it)) }

        Log.d(TAG, "getLocalRecipesList        ${recipes.size} END")

        return recipes.toList()
    }


    private fun mapToRecipe(recipe: RecipeEntity) : Recipe2 {
        return Recipe2(
            id = recipe.id,
            calories = recipe.calories
       )
    }

    private fun mapToRecipeEntity(recipe: Recipe2) : RecipeEntity {
        return RecipeEntity(
            id = null,
            name = recipe.name,
            url = null,
            calories = recipe.calories
       )
   }
}