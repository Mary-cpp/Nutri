package com.example.nutri.di

import android.content.Context
import com.example.nutri.data.bmi.database.UserDatabase
import com.example.nutri.data.bmi.repository.UserDataBaseGateway
import com.example.nutri.data.bmi.repository.UserDatabaseGatewayImpl
import com.example.nutri.domain.bmi.interactor.BmiInteractor
import com.example.nutri.domain.bmi.interactor.BmiInteractorImpl
import com.example.nutri.domain.bmi.interactor.CountBMI
import com.example.nutri.domain.bmi.interactor.CountBmiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
        database: UserDatabase
    ) : UserDataBaseGateway
    = UserDatabaseGatewayImpl(database = database)

    @Provides
    @Singleton
    fun provideDatabase (@ApplicationContext context: Context) : UserDatabase {
        return UserDatabase.getDatabase(context = context)
    }
}