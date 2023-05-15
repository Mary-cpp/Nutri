package com.example.nutri.domain.statistics

import com.example.nutri.domain.statistics.model.Water
import java.util.Date

interface WaterUseCaseInteractor {
    suspend fun updateData(info: Water)
    suspend fun loadData(date: Date) : Water
}