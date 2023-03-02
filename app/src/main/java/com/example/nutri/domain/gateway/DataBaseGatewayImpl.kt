package com.example.nutri.domain.gateway

import android.util.Log
import com.example.nutri.domain.model.Recipe
import javax.inject.Inject

class DataBaseGatewayImpl @Inject constructor() : DataBaseGateway {

    val TAG: String = "DataBaseGatewayImpl"

    private var listOfRecipes: MutableList<Recipe> = arrayListOf()
    override suspend fun saveToLocal(recipe: Recipe, recipeName: String): String {
        listOfRecipes.add(recipe)

        Log.d(TAG, "Recipe from local: ${recipe}" )
        return recipeName
    }

    override fun getLocalRecipesList() : MutableList<Recipe> = listOfRecipes

    private fun myMap() {TODO("ADD MAPPERS")}
//    private fun toReceipe(recipe: RecipeEntity) : Recipe {
//        return Recipe(
//            url = recipe.uri,
//            name =
//        )
//    }
//    private fun mapRecipeToEntity(recipe: Recipe) : RecipeEntity {
//        return RecipeEntity(
//            url = recipe.uri,
//            name =
//        )
//    }

}