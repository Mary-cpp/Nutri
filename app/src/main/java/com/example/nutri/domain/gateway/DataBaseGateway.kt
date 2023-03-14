package com.example.nutri.domain.gateway

import com.example.nutri.domain.model.Recipe2

interface DataBaseGateway {

    suspend fun saveToLocal(recipe: Recipe2, recipeName: String): String
    suspend fun getLocalRecipesList(): List<Recipe2>
}