package com.example.nutri.data

import android.util.Log
import com.example.nutri.data.gateway.DataBaseGateway
import com.example.nutri.domain.model.Recipe
import javax.inject.Inject

const val TAG: String = "DataBaseGatewayImpl"

class DataBaseGatewayImpl @Inject constructor() : DataBaseGateway {

    private var listOfRecipes: MutableList<Recipe> = arrayListOf()
    override suspend fun saveToLocal(recipe: Recipe) {
        listOfRecipes.add(recipe)

        Log.d(TAG, "Recipe from local: ${recipe.toString()}" )
    }

    fun getLocalRecipesList() : MutableList<Recipe> = listOfRecipes

}