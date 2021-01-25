package com.example.movies.ui.movieitem

import androidx.lifecycle.MutableLiveData
import com.example.movies.MovieItemModel
import com.example.movies.shared.data.MoviesItem
import com.example.movies.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    var movieItem: MutableLiveData<MovieItemModel> = MutableLiveData()

    fun setupItem(item: MovieItemModel){
        this.movieItem.value = item
    }
}