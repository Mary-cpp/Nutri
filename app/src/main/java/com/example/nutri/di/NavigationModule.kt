package com.example.nutri.di

import com.example.nutri.core.MyNavController
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
    fun provideNavController(): MyNavController
            = MyNavController()
}