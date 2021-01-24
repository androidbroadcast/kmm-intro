package com.example.movies.shared

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}