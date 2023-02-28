package com.example.nutri.domain

import com.example.nutri.domain.model.Recipe

interface RecipeInteractor {

    suspend fun retrieveRecipe (param: String = "nothing"): Recipe

}
