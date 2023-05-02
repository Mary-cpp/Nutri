package com.example.nutri.domain.statistics

import java.util.*

interface WaterDatabaseGateway {
    suspend fun updateWaterData(info: Water)
    suspend fun getWaterDataFromDb(date: Date) : Water
}