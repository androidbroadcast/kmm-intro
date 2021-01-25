package com.example.movies.shared.service

import com.example.movies.shared.data.MoviesList
import com.example.movies.shared.network.Configuration
import com.example.movies.shared.network.NetworkService
import com.example.movies.shared.network.response.ContentResponse
import kotlin.native.concurrent.ThreadLocal


class MoviesService {
    @ThreadLocal
    companion object {
        val instance = MoviesService()
    }

    private val networkService: NetworkService = NetworkService()

    suspend fun loadMovies():ContentResponse<MoviesList> {
        val url = "discover/movie?api_key=${Configuration.API_KEY}&page=1&sort_by=popularity.desc"
       return networkService.loadContentData<MoviesList>(url)
    }

    suspend fun searchMovies(query: String):ContentResponse<MoviesList> {
        val url = "search/movie?api_key=${Configuration.API_KEY}&query=${query}&page=1"
        return networkService.loadContentData<MoviesList>(url)
    }
}