package com.example.nutri.domain.gateway

import android.util.Log
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.entity.Label
import com.example.nutri.data.entity.RecipeEntity
import com.example.nutri.domain.model.Recipe
import javax.inject.Inject

class DataBaseGatewayImpl @Inject constructor(
    val database: RecipeDatabase
    ) : DataBaseGateway {

    val TAG: String = "DataBaseGatewayImpl"



    override suspend fun saveToLocal(recipe: Recipe, recipeName: String): String {
        Log.d(TAG, "saveToLocal         START")

        val recipeEnt = mapToRecipeEntity(recipe)

        recipeEnt.name = recipeName

        database.recipeDAO().add(recipeEnt)
        saveLabels(mapLabelsToEntity(recipe.dietLabels, recipeId = recipeEnt.id!!, "dietLabels"))
        //saveLabels(mapLabelsToEntity(recipe.dietLabels, recipeId = re))

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


    private fun saveLabels(labels : List<Label>) : Int{
        Log.d(TAG, "Inserting recipe labels         START")

        database.recipeDAO().addLabels(labels)

        Log.d(TAG, "Inserting recipe labels         END")

        return labels.size
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

    private fun mapLabelsToEntity(labels:  List<String>, recipeId: Int, category: String) : List<Label>{

        val entityLabels : MutableList<Label> = mutableListOf()

        labels.forEach{
            entityLabels.add(Label(id = null,
                recipeId = recipeId,
                text = it,
                category = category
            ))
        }

        return entityLabels.toList()
    }
}