package com.example.movies.response

import com.google.gson.annotations.SerializedName

enum class Error {
    Network, Auth, Tech, Other, NotFound, BadAnswer
}

class ErrorResponse constructor() {
    @SerializedName("status")
    var code: Int = 0
    @SerializedName("message")
    var message: String = ""

    var type: Error = Error.Other

    constructor(type: Error) : this() {
        this.type = type
    }

    constructor(e: Exception) : this() {
        this.type = Error.Other
        this.message = e.message ?: ""
    }

    constructor(message: String) : this() {
        this.type = Error.Other
        this.message = message
    }
}
