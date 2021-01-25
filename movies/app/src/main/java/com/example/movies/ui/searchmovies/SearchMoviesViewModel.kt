package com.example.movies.ui.searchmovies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.shared.data.MoviesItem
import com.example.movies.shared.service.MoviesService
import com.example.movies.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchMoviesViewModel() : BaseViewModel() {
    private val service = MoviesService.instance
    val moviesList: MutableLiveData<List<MoviesItem>> = MutableLiveData()

    fun queryMovies(query: String = "") {
        viewModelScope.launch {
            val result = service.searchMovies(query)
            moviesList.value = result?.results ?: arrayListOf()
        }
    }

    fun getMovie(index: Int): MoviesItem? {
        return  moviesList.value?.get(index)
    }
}