package com.example.nutri.di

import com.example.nutri.data.bmi.repository.UserDataBaseGateway
import com.example.nutri.data.bmi.repository.UserDatabaseGatewayImpl
import com.example.nutri.data.recipe.local.database.RecipeDatabase
import com.example.nutri.domain.bmi.interactor.BmiInteractor
import com.example.nutri.domain.bmi.interactor.BmiInteractorImpl
import com.example.nutri.domain.bmi.interactor.CountBMI
import com.example.nutri.domain.bmi.interactor.CountBmiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object BmiModule {

    @Provides()
    fun provideUseCase(
        dataBaseGateway: UserDataBaseGateway,
        countBMI: CountBMI
    ) : BmiInteractor
    = BmiInteractorImpl(saver = dataBaseGateway, counter = countBMI)

    @Provides
    fun provideCounter(
    ): CountBMI
    = CountBmiImpl()

    @Provides
    fun provideDatabaseGateway(
        database: RecipeDatabase
    ) : UserDataBaseGateway
    = UserDatabaseGatewayImpl(database = database)
}