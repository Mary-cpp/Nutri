package com.example.nutri.domain.statistics

import com.example.nutri.domain.statistics.model.Water
import java.util.*

class WaterUseCaseInteractorImpl(
    val db: WaterDatabaseGateway
) : WaterUseCaseInteractor{
    override suspend fun loadData(date: Date): Water = db.getWaterInfoByDate(date)
    override suspend fun updateData(info: Water) = db.updateWaterData(info)
}