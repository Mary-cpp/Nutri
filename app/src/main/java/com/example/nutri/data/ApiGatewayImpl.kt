package com.example.nutri.data

import android.util.Log
import com.example.nutri.data.gateway.ApiGateway
import com.example.nutri.data.api.EdamamService
import com.example.nutri.domain.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiGatewayImpl : ApiGateway {

    private val baseUrl = "https://api.edamam.com/api/"

    private val logTag = "ApiGatewayImpl"
    private val appId = "c4784311"
    private val appKey = "ea0d71aa81a3a366d9d7cc58783563ef"

    private fun connect(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    override suspend fun recieveRecipeData(param: String): Recipe {

        val request: EdamamService = connect().create(EdamamService::class.java)

        var response: Recipe

        withContext(Dispatchers.IO){
            // we should response with a sealed class
            response = request.getNutritionSpecs(appId, appKey, param)
            Log.d(logTag, "response body: $response")

            return@withContext response
        }

        return response
    }
}