package com.example.nutri.domain.interactor

import com.example.nutri.domain.model.Recipe

interface RecipeInteractor {

    suspend fun retrieveRecipe (param: String): Recipe
}
