package com.example.nutri.data.api

import com.example.nutri.domain.recipes.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamService {

    @GET("nutrition-data")
    suspend fun getNutritionSpecs (
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String,
        @Query("ingr") ingr: String
    ): Recipe
}