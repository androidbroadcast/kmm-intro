package com.example.movies.network

import com.example.movies.shared.data.MoviesList
import com.example.movies.shared.network.Configuration
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    @GET("discover/movie")
    suspend fun loadMovies(
        @Query("api_key")key: String = Configuration.API_KEY,
        @Query("page")page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"):Response<MoviesList>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key")key: String = Configuration.API_KEY,
        @Query("query")query: String,
        @Query("page")page: Int = 1):Response<MoviesList>
}