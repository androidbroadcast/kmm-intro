package com.example.movies.ui.movieslist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.MoviesItem
import com.example.movies.moviesService.MoviesService
import com.example.movies.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel @ViewModelInject constructor(private val service: MoviesService) : BaseViewModel() {

    val moviesList: MutableLiveData<List<MoviesItem>> = MutableLiveData()

    fun loadMovies() {
        viewModelScope.launch {
            val result = service.loadMovies()
            moviesList.value = result.content?.results ?: arrayListOf()
        }
    }

    fun getMovie(index: Int): MoviesItem? {
        return  moviesList.value?.get(index)
    }
}