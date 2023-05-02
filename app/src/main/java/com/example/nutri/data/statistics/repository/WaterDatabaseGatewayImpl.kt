package com.example.nutri.data.statistics.repository

import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.domain.statistics.Water
import com.example.nutri.domain.statistics.WaterDatabaseGateway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class WaterDatabaseGatewayImpl @Inject constructor(
    val database: RecipeDatabase
): WaterDatabaseGateway{
    override suspend fun getWaterDataFromDb(date: Date): Water{
        return withContext(Dispatchers.IO){ database.waterDAO().getWaterInfoByDate(date) ?: Water(Date())}
    }

    override suspend fun updateWaterData(info: Water) {
        withContext(Dispatchers.IO){ database.waterDAO().addWater(info)}
    }
}