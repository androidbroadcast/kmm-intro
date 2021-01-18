package com.example.movies.response

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Mapper {
    companion object {
        private var instance = Mapper()
        var Instance: Mapper = instance
    }

    fun <T> map(response: Response<T>): ContentResponse<T> {
        var content = response.body()
        var contentResponse = ContentResponse<T>()
        contentResponse.content = content
        if (response.code() >= 400 || response.code() <200) {

            try {
                contentResponse.errorResponse = processBody(response.errorBody()?.string() ?: "", response.code())
            } catch (ie: Exception) {
                val error = ErrorResponse()
                error.code = response.code()
                contentResponse.errorResponse = error
            }
        }
        return contentResponse
    }

    fun <T> map(content: String, clazz: Class<T>): ContentResponse<T> {
        val contentResponse = ContentResponse<T>()
        val gson = GsonBuilder().create()
        contentResponse.content = gson.fromJson(content, clazz)
        return contentResponse
    }

    fun <T> map(error: Throwable): ContentResponse<T> {

        var errorResponse = ErrorResponse()

        when (error) {
            is HttpException -> {
                val body = error.response()?.errorBody()?.string()
                processBody(body ?: "", error.code())
            }

            is UnknownHostException,
            is SocketTimeoutException -> {
                errorResponse.type = Error.Network
                errorResponse.message = "Отсутствует сетевое соединение"
            }
            is JsonSyntaxException -> {
                errorResponse.type = Error.Other
                errorResponse.message = "Неправильная структура данных"
            }
            else -> {
                errorResponse.type = Error.Network
                errorResponse.message = "Проблема соединения с сервером"
            }
        }
        var contentResponse = ContentResponse<T>()
        contentResponse.errorResponse = errorResponse

        return contentResponse
    }

    private fun processBody(body: String, code: Int): ErrorResponse {
        var errorResponse = ErrorResponse()
        val gson = GsonBuilder().create()
        var errorBody = gson.fromJson(body, ErrorResponse::class.java)
        if (errorBody != null) {
            errorResponse = errorBody
        }
        errorResponse.code = code
        when (code) {
            400 -> errorResponse.type = Error.BadAnswer
            401, 403 -> errorResponse.type = Error.Auth
            404 -> errorResponse.type = Error.NotFound
            500, 503 -> errorResponse.type = Error.Tech
            else -> errorResponse.type = Error.Other
        }
        return errorResponse
    }
}
