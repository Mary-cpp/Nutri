package com.example.nutri.di

import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.statistics.repository.MealDataBaseGateway
import com.example.nutri.data.statistics.repository.MealDatabaseGatewayImpl
import com.example.nutri.data.statistics.repository.WaterDatabaseGatewayImpl
import com.example.nutri.domain.statistics.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object StatisticsModule {

    @Provides
    fun provideUseCase(
        dataBaseGateway: MealDataBaseGateway
    ) : MealInteractor
    = MealInteractorImpl(db = dataBaseGateway)


    @Provides
    fun provideDatabaseGateway(
        database: RecipeDatabase
    ) : MealDataBaseGateway
    = MealDatabaseGatewayImpl(database = database)

    @Provides
    fun provideWaterDatabaseGateway(
        database: RecipeDatabase
    ) : WaterDatabaseGateway
    = WaterDatabaseGatewayImpl(database = database)

    @Provides
    fun provideWaterUseCaseInteractor(
        dataBaseGateway: WaterDatabaseGateway
    ) : WaterUseCaseInteractor
    = WaterUseCaseInteractorImpl(db = dataBaseGateway)
}