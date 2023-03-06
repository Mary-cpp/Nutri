package com.example.nutri.di

import com.example.nutri.domain.gateway.ApiGateway
import com.example.nutri.domain.gateway.ApiGatewayImpl
import com.example.nutri.domain.gateway.DataBaseGatewayImpl
import com.example.nutri.domain.interactor.RecipeInteractor
import com.example.nutri.domain.interactor.RecipeInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object GetRecipeFromApiModule {

    @Provides
    fun provideUseCase(
        api: ApiGatewayImpl,
        db: DataBaseGatewayImpl
    ): RecipeInteractor {
        return RecipeInteractorImpl(api = api,db = db)
    }

    @Provides
    fun provideApiGateway() : ApiGateway {
        return ApiGatewayImpl()
    }
}