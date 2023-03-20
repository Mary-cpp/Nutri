package com.example.nutri.data.repository

import com.example.nutri.data.api.EdamamService
import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ApiGatewayImpl @Inject constructor() : ApiGateway {

    private val baseUrl = "https://api.edamam.com/api/"
    private val appId = "c4784311"
    private val appKey = "ea0d71aa81a3a366d9d7cc58783563ef"

    private fun client(): EdamamService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EdamamService::class.java)

    override suspend fun receiveRecipeData(param: String): Recipe {

        // we should response with a sealed class

        return withContext(Dispatchers.IO) { client().getNutritionSpecs(appId, appKey, param) }
    }
}
