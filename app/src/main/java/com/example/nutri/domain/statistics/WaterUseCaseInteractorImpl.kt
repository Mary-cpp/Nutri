package com.example.nutri.domain.statistics

import java.util.*

class WaterUseCaseInteractorImpl(
    val db: WaterDatabaseGateway
) : WaterUseCaseInteractor{
    override suspend fun loadData(date: Date): Water = db.getWaterDataFromDb(date)
    override suspend fun updateData(info: Water) = db.updateWaterData(info)
}