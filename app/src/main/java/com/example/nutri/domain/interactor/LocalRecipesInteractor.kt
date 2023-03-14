package com.example.nutri.domain.interactor

import com.example.nutri.domain.model.Recipe2

interface LocalRecipesInteractor {

    suspend fun saveRecipe(recipe: Recipe2, recipeName: String): String
    suspend fun receiveRecipes(): List<Recipe2>
}