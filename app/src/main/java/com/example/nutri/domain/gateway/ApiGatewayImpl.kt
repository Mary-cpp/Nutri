package com.example.nutri.domain.gateway

import android.util.Log
import com.example.nutri.data.api.EdamamService
import com.example.nutri.domain.model.Recipe
import com.example.nutri.domain.model.Recipe2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ApiGatewayImpl @Inject constructor() : ApiGateway {

    private val baseUrl = "https://api.edamam.com/api/"

    private val logTag = "ApiGatewayImpl"
    private val appId = "c4784311"
    private val appKey = "ea0d71aa81a3a366d9d7cc58783563ef"

    private fun client(): EdamamService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EdamamService::class.java)

    override suspend fun receiveRecipeData(param: String): Recipe2 {

        // we should response with a sealed class
        val response: Recipe =
            withContext(Dispatchers.IO) { client().getNutritionSpecs(appId, appKey, param) }
        Log.d(logTag, "response body: $response")

        return mapToDomainRecipe(response)
    }

    fun mapToDomainRecipe(recipe: Recipe)
            = Recipe2(
        id = recipe.id,
        name = "",
        calories = recipe.calories,
        totalWeight = recipe.totalWeight,
        dietLabels = recipe.dietLabels,
        healthLabels = recipe.healthLabels!!,
        cautions = recipe.cautions,
        totalNutrients = recipe.totalNutrients!!.mapToListOfNutrients(),
        totalDaily = recipe.totalNutrients.mapToListOfNutrients(),
        parsedIngredientString = recipe.ingredients?.get(0)!!.text,
        ingredients = recipe.ingredients[0].parsed,
        totalNutrientsKCal = recipe.totalNutrientsKCal
    )

}
