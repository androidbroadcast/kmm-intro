package com.example.movies

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AppContext = this
    }

    companion object {
        @JvmStatic
        lateinit var INSTANCE: MoviesApp
        var AppContext: Context? = null
    }
}