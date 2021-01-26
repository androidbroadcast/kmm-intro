package com.example.movies.shared.viewmodel

import com.example.movies.shared.data.MoviesItem
import com.example.movies.shared.uiDispatcher

class MovieItemModel : BaseViewModel(uiDispatcher) {
    private var item: MoviesItem? = null
    var movieItem: LiveData<MoviesItem> = LiveData()

    fun setupItem(item: MoviesItem){
       this.item = item
    }

    fun load() {
        this.movieItem.value = item
    }
}