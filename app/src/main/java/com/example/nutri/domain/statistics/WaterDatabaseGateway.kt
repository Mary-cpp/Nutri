package com.example.nutri.domain.statistics

import com.example.nutri.domain.statistics.model.Water
import java.util.*

interface WaterDatabaseGateway {
    suspend fun updateWaterData(info: Water)
    suspend fun getWaterInfoByDate(date: Date) : Water
}