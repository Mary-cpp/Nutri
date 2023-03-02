package com.example.nutri.di

import com.example.nutri.domain.gateway.ApiGatewayImpl
import com.example.nutri.domain.gateway.ApiGateway
import com.example.nutri.domain.interactor.ReceiveRecipeFromApiUseCase
import com.example.nutri.domain.interactor.RecipeInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object GetRecipeFromApiModule {

    @Provides
    fun provideUseCase(
        api: ApiGatewayImpl
    ): RecipeInteractor {
        return ReceiveRecipeFromApiUseCase(api = api)
    }

    @Provides
    fun provideApiGateway() : ApiGateway {
        return ApiGatewayImpl()
    }
}