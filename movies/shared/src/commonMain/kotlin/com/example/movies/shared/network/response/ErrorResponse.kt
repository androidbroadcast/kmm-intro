package com.example.movies.shared.network.response

import kotlinx.serialization.SerialName

class ErrorResponse constructor() {
    @SerialName("status")
    var code: Int = 0
    @SerialName("message")
    var message: String = ""


    constructor(e: Exception) : this() {
        this.message = e.message ?: ""
    }

    constructor(message: String) : this() {
        this.message = message
    }
}
