package com.example.nutri.data.recipe.remote.repository

import android.util.Log
import com.example.nutri.core.ResultState
import com.example.nutri.data.api.EdamamService
import com.example.nutri.data.recipe.remote.dto.nutrients.BaseNutrient
import com.example.nutri.data.recipe.remote.dto.nutrients.BaseNutrientInstanceCreator
import com.example.nutri.domain.recipes.model.Recipe
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ApiGatewayImpl @Inject constructor() : ApiGateway {

    private val baseUrl = "https://api.edamam.com/api/"
    private val appId = "c4784311"
    private val appKey = "ea0d71aa81a3a366d9d7cc58783563ef"

    private val TAG = this::class.simpleName

    private val gson = GsonBuilder()
        .registerTypeAdapter(
            BaseNutrient::class.java,
            BaseNutrientInstanceCreator()
        ).create()

    private fun client(): EdamamService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(EdamamService::class.java)

    override suspend fun receiveRecipeData(param: String): ResultState<Recipe> {
        return try{
            ResultState.Success(withContext(Dispatchers.IO) { client().getNutritionSpecs(appId, appKey, param) })
        } catch(e: HttpException){
            Log.e(TAG, "Caught an error $e")
            ResultState.Error(exception = e)
        }
    }
}
