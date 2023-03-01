package com.example.nutri.domain.interactor

import com.example.nutri.domain.model.Recipe

interface LocalRecipesInteractor {

    suspend fun saveRecipe(recipe: Recipe , recipeName: String): String
    suspend fun receiveRecipes(): List<Recipe>
}