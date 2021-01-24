package com.example.movies.shared.data

import kotlinx.serialization.Serializable

@Serializable
data class MoviesList(val page: Int,val results: List<MoviesItem>)