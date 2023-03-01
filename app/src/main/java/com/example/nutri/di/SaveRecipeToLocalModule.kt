package com.example.nutri.di

import com.example.nutri.data.DataBaseGatewayImpl
import com.example.nutri.data.gateway.DataBaseGateway
import com.example.nutri.domain.interactor.SaveRecipeInteractor
import com.example.nutri.domain.interactor.SaveRecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object SaveRecipeToLocalModule {

    @Provides
    fun provideUseCase(
        dataBaseGatewayImpl: DataBaseGatewayImpl
    ) : SaveRecipeInteractor{
        return SaveRecipeUseCase(db = dataBaseGatewayImpl
        )
    }

    @Provides
    fun provideDbGateway(): DataBaseGateway {
        return DataBaseGatewayImpl()
    }
}