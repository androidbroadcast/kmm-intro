package com.example.movies.ui.movieslist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.shared.data.MoviesItem
import com.example.movies.shared.service.MoviesService
import com.example.movies.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MoviesListViewModel() : BaseViewModel() {
private val service = MoviesService.instance

    val moviesList: MutableLiveData<List<MoviesItem>> = MutableLiveData()

    fun loadMovies() {
        viewModelScope.launch {
            val result = service.loadMovies()
            moviesList.value = result?.results
        }
    }

    fun getMovie(index: Int): MoviesItem? {
        return  moviesList.value?.get(index)
    }
}