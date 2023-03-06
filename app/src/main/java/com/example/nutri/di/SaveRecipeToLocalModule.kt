package com.example.nutri.di

import android.content.Context
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.domain.gateway.DataBaseGateway
import com.example.nutri.domain.gateway.DataBaseGatewayImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object SaveRecipeToLocalModule {

    @Provides
    fun provideDbGateway(
        database: RecipeDatabase
    ): DataBaseGateway {
        return DataBaseGatewayImpl(
            database = database
        )
    }


    @Provides
    @Singleton
    fun provideDatabase (@ApplicationContext context: Context) : RecipeDatabase {
        return RecipeDatabase.getDatabase(context = context)
    }
}