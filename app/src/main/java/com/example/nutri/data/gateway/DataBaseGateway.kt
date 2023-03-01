package com.example.nutri.data.gateway

import com.example.nutri.domain.model.Recipe

interface DataBaseGateway {

    suspend fun saveToLocal(recipe: Recipe)
}