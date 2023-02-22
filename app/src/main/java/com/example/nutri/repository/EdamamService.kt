package com.example.nutri.repository

import com.example.nutri.domain.entity.Recipe
import retrofit2.Call
import retrofit2.http.GET

interface EdamamService {

    @GET
    suspend fun getNutritionSpecs(): Call<List<Recipe>>
}