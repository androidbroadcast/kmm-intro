package com.example.movies.shared.viewmodel

import com.example.movies.shared.data.MoviesItem
import com.example.movies.shared.data.MoviesList
import com.example.movies.shared.service.MoviesService
import com.example.movies.shared.uiDispatcher
import kotlinx.coroutines.launch

class MoviesListViewModel():BaseViewModel(uiDispatcher) {
    private val service = MoviesService.instance

    val moviesList: LiveData<MoviesList> = LiveData()

    fun loadMovies() {
       scope.launch {
            val result = service.loadMovies()
            moviesList.value = result
        }
    }

    fun getMovie(index: Int): MoviesItem? {
        return  moviesList.value?.results?.get(index)
    }
}
