package com.example.movies.data

import com.google.gson.annotations.SerializedName


class MoviesItem {
    var id: Long = 0
    var title: String? = ""
    var overview: String? = ""
    @SerializedName("poster_path")
    var posterPath: String? = ""

    @SerializedName("release_date")
    var releaseDate:String? = ""
    fun imagePath():String  = "http://image.tmdb.org/t/p/w300${posterPath}"
}