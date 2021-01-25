package com.example.movies.shared.service

import com.example.movies.shared.data.MoviesList
import com.example.movies.shared.network.Configuration
import com.example.movies.shared.network.NetworkClient
import kotlin.native.concurrent.ThreadLocal

class MoviesService {
    @ThreadLocal
    companion object {
        val instance = MoviesService()
    }
    private val networkClient = NetworkClient()

    suspend fun loadMovies(): MoviesList? {
        val url = "discover/movie?api_key=${Configuration.API_KEY}&page=1&sort_by=popularity.desc"
        return networkClient.loadContentData(url)
    }

    suspend fun searchMovies(query: String): MoviesList? {
        val url = "search/movie?api_key=${Configuration.API_KEY}&page=1&query=${query}"
        return networkClient.loadContentData(url)
    }

    /*
    suspend fun searchMovies(query: String): ContentResponse<MoviesList> {
        val result = api.searchMovies(query = query)
        return ContentResponse<MoviesList>(result)
    }*/
}