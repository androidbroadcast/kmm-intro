package com.example.movies.ui.searchmovies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.MoviesItem
import com.example.movies.moviesService.MoviesService
import com.example.movies.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchMoviesViewModel @ViewModelInject constructor(private val service: MoviesService) : BaseViewModel() {
    val moviesList: MutableLiveData<List<MoviesItem>> = MutableLiveData()

    fun queryMovies(query: String = "") {
        uiScope.launch {
            val result = service.searchMovies(query)
            moviesList.value = result.content?.results ?: arrayListOf()
        }
    }

    fun getMovie(index: Int): MoviesItem? {
        return  moviesList.value?.get(index)
    }
}