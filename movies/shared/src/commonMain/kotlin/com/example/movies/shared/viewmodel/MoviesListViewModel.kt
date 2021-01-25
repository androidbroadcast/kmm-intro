package com.example.movies.shared.viewmodel

import com.example.movies.shared.data.MoviesItem
import com.example.movies.shared.data.MoviesList
import com.example.movies.shared.service.MoviesService
import com.example.movies.shared.util.ioDispatcher
import com.example.movies.shared.util.uiDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesListViewModel : BaseViewModel(ioDispatcher) {
    private val service = MoviesService.instance
    var moviesItems: LiveData<MoviesList> = LiveData()


    fun loadMovies() {
       scope.launch {
          val result =  service.loadMovies()
           withContext(uiDispatcher) {
               moviesItems.value = result.content
           }
       }
    }

    fun getMovie(index: Int): MoviesItem? {
        return moviesItems.value?.results?.get(index)
    }

}