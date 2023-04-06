package com.example.nutri.di

import com.example.nutri.ui.navigation.NavControllerHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NavigationModule {

    @Singleton
    @Provides
    fun provideNavController(): NavControllerHolder
            = NavControllerHolder()
}