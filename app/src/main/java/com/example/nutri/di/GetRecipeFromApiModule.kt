package com.example.nutri.di

import android.content.Context
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.domain.gateway.ApiGatewayImpl
import com.example.nutri.domain.gateway.ApiGateway
import com.example.nutri.domain.gateway.DataBaseGatewayImpl
import com.example.nutri.domain.interactor.RecipeInteractorImpl
import com.example.nutri.domain.interactor.RecipeInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


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

    @Provides
    @Singleton
    fun provideDatabase (@ApplicationContext context: Context) : RecipeDatabase {
        return RecipeDatabase.getDatabase(context = context)
    }
}