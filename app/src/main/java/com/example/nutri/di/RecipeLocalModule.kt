package com.example.nutri.di

import android.content.Context
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.domain.recipes.RecipeDatabaseGateway
import com.example.nutri.data.recipe.local.repository.RecipeDatabaseGatewayImpl
import com.example.nutri.domain.recipes.interactor.LocalRecipeUseCase
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RecipeLocalModule {

    @Provides
    fun provideUseCase(
        dataBaseGatewayImpl: RecipeDatabaseGatewayImpl
    ) : LocalRecipesInteractor {
        return LocalRecipeUseCase(db = dataBaseGatewayImpl
        )
    }

    @Provides
    fun provideDbGateway(
        database: RecipeDatabase
    ): RecipeDatabaseGateway {
        return RecipeDatabaseGatewayImpl(
            database = database
        )
    }


    @Provides
    @Singleton
    fun provideDatabase (@ApplicationContext context: Context) : RecipeDatabase {
        return RecipeDatabase.getDatabase(context = context)
    }
}