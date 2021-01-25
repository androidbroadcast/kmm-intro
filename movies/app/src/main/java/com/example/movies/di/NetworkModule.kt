package com.example.movies.di

import com.example.movies.shared.network.Configuration
import com.example.movies.network.MoviesAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    @Named("apiClient")
    fun provideOkHttpClient(): OkHttpClient {
        var builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    @Named("retrofit")
    fun provideRetrofit(
        @Named("apiClient") client: OkHttpClient,
        converterFactory: Converter.Factory
    ) =
        Retrofit.Builder()
            .baseUrl(Configuration.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(converterFactory)
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideApi(@Named("retrofit") retrofit: Retrofit) = retrofit.create(MoviesAPI::class.java)
}
