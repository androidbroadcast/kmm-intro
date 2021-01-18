package com.example.movies.response

import retrofit2.Response

class ContentResponse<T> constructor() {
    var content: T? = null
    var errorResponse: ErrorResponse? = null

    constructor(response: Response<T>) : this() {

        var data = Mapper.Instance.map(response)
        content = data.content
        errorResponse = data.errorResponse
    }

    constructor(error: Error) : this() {
        errorResponse = ErrorResponse(error)
    }

    constructor(error: ErrorResponse) : this() {
        this.errorResponse = error
    }
}