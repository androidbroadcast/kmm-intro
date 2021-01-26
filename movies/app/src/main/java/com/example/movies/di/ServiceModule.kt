package com.example.movies.di

import com.example.movies.moviesService.MoviesService
import com.example.movies.network.MoviesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ServiceModule {
    @Singleton
    @Provides
    fun provideMoviesService(api: MoviesAPI) = MoviesService(api)
}