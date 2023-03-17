package com.example.nutri.data.repository

import com.example.nutri.data.entity.RecipeEntityCommon
import com.example.nutri.domain.model.Recipe

interface DataBaseGateway {

    suspend fun saveToLocal(recipe: Recipe, recipeName: String): Int
    suspend fun getLocalRecipesList(): List<Recipe>
    suspend fun getRecipe(recipeId: Int) : Recipe
}