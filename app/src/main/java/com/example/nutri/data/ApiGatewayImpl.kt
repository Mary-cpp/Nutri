package com.example.nutri.data

import android.util.Log
import com.example.nutri.domain.gateway.ApiGateway
import com.example.nutri.data.api.EdamamService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiGatewayImpl : ApiGateway {

    private val baseUrl = "https://api.edamam.com/api/"

    private val logTag = "RETROFIT"
    private val appId = "c4784311"
    private val appKey = "ea0d71aa81a3a366d9d7cc58783563ef"

    private fun connect(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    override suspend fun recieveRecipeData(param: String): String {

        val request: EdamamService = connect().create(EdamamService::class.java)

        val response = request.getNutritionSpecs(appId, appKey, param)
        var recipe: String

        Log.d(logTag, "response body: ${response.body().toString()}")

        withContext(Dispatchers.IO){
            if (response.isSuccessful){
                recipe = response.body().toString()
                Log.d(logTag, response.body().toString())
            }
            else recipe = response.code().toString()
            Log.e(logTag, response.code().toString())
        }

        return recipe
    }
}