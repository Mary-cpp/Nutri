package com.example.nutri.domain.statistics

import java.util.Date

interface WaterUseCaseInteractor {
    suspend fun updateData(info: Water)
    suspend fun loadData(date: Date) : Water
}