package com.example.nutri.domain.gateway

import android.util.Log
import com.example.nutri.data.entity.RecipeEntity
import com.example.nutri.domain.model.Recipe
import javax.inject.Inject

class DataBaseGatewayImpl @Inject constructor() : DataBaseGateway {

    val TAG: String = "DataBaseGatewayImpl"
    private var listOfRecipes: MutableList<RecipeEntity> = arrayListOf()

    override suspend fun saveToLocal(recipe: Recipe, recipeName: String): String {

        listOfRecipes.add(mapToRecipeEntity(recipe, recipeName))

        Log.d(TAG, "List to save: ${listOfRecipes.toString()}")

        return recipeName
    }

    override fun getLocalRecipesList() : List<Recipe> {

        val listOfDomainRecipes: MutableList<Recipe> = arrayListOf()

        listOfRecipes.forEach {
            listOfDomainRecipes.add(mapToDomainRecipe(it))
        }

        Log.d(TAG, "List to get: ${listOfDomainRecipes.toString()}")

        return listOfDomainRecipes.toList()
    }


    private fun mapToDomainRecipe(recipe: RecipeEntity) : Recipe {
        return Recipe(
            uri = recipe.url,
            calories = recipe.calories.toLong(),
            dietLabels = recipe.dietLabels,
            totalWeight = recipe.totalWeight,
            healthLabels = recipe.healthLabels,
            ingredients = recipe.ingredients,
            cautions = recipe.cautions,

            // need to parse this fields correctly before putting real values
            totalNutrients = null,
            totalDaily = null,
            totalNutrientsKCal = null
       )
    }

    private fun mapToRecipeEntity(recipe: Recipe, recipeName: String) : RecipeEntity {
        return RecipeEntity(
            url = recipe.uri,
            name = recipeName,
            calories = recipe.calories!!.toDouble(),
            dietLabels = recipe.dietLabels,
            totalWeight = recipe.totalWeight,
            healthLabels = recipe.healthLabels,
            ingredients = recipe.ingredients,
            cautions = recipe.cautions
       )
   }
}