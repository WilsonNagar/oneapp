package com.oneapp.app

import com.oneapp.network.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}

