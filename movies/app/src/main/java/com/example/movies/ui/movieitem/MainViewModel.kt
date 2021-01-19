package com.example.movies.ui.movieitem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.MoviesItem
import com.example.movies.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    var movieItem: MutableLiveData<MoviesItem> = MutableLiveData()

    fun setupItem(item: MoviesItem){
        this.movieItem.value = item
    }
}