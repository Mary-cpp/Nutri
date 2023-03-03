package com.example.nutri.domain.gateway

import com.example.nutri.domain.model.Recipe

interface DataBaseGateway {

    suspend fun saveToLocal(recipe: Recipe, recipeName: String): String
    suspend fun getLocalRecipesList(): List<Recipe>
}