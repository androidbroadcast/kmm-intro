package com.example.movies.shared.viewmodel

import com.example.movies.shared.data.MoviesItem
import com.example.movies.shared.data.MoviesList
import com.example.movies.shared.service.MoviesService
import com.example.movies.shared.uiDispatcher
import kotlinx.coroutines.launch

class SearchViewModel :BaseViewModel(uiDispatcher) {
    private val service = MoviesService.instance

    val moviesList: LiveData<MoviesList> = LiveData()

    fun queryMovies(query: String = "") {
        scope.launch {
            val result = service.searchMovies(query)
            moviesList.value = result
        }
    }

    fun getMovie(index: Int): MoviesItem? {
        return  moviesList.value?.results?.get(index)
    }
}
