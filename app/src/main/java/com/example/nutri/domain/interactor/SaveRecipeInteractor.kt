package com.example.nutri.domain.interactor

import com.example.nutri.domain.model.Recipe

interface SaveRecipeInteractor {

    suspend fun saveRecipe(recipe: Recipe)
}