package com.example.movies.shared.network.response

class ContentResponse<T> constructor() {
    var content: T? = null
    var errorResponse: ErrorResponse? = null

    constructor(error: ErrorResponse) : this() {
        this.errorResponse = error
    }
}