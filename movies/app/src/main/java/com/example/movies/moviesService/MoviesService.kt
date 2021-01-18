package com.example.movies.moviesService

import com.example.movies.data.MoviesList
import com.example.movies.network.Configuration
import com.example.movies.network.MoviesAPI
import com.example.movies.response.ContentResponse
import javax.inject.Inject

class MoviesService @Inject constructor(private val api: MoviesAPI) {
    suspend fun loadMovies(): ContentResponse<MoviesList> {
        val result = api.loadMovies()
        return ContentResponse<MoviesList>(result)
    }

    suspend fun searchMovies(query: String): ContentResponse<MoviesList> {
        val result = api.searchMovies(query = query)
        return ContentResponse<MoviesList>(result)
    }
}