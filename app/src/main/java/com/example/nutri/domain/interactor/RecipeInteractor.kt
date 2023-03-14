package com.example.nutri.domain.interactor

import com.example.nutri.domain.model.Recipe2

interface RecipeInteractor {

    suspend fun retrieveRecipe (param: String): Recipe2
}
