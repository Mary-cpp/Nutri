package com.example.nutri.domain.recipes.interactor

import com.example.nutri.domain.recipes.model.Recipe

interface RecipeInteractor {

    suspend fun retrieveRecipe (param: String): Recipe
}
