package com.example.movies.shared.network

import com.example.movies.shared.network.response.ContentResponse
import com.example.movies.shared.network.response.ErrorResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NetworkService {
    val httpClient = HttpClient {
        install(JsonFeature) {
            val jsonDecoder = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(jsonDecoder)
        }
    }

    suspend inline fun <reified T> loadData(url: String): T? {
        return httpClient.get(url)
    }

    suspend inline fun <reified T> loadContentData(url: String): ContentResponse<T> {
        var contentResponse = ContentResponse<T>()

        try {

            val json = httpClient.get<String> {
                url {
                    protocol = URLProtocol.HTTP
                    host = Configuration.BASE_URL
                    encodedPath = url
                }
            }
            print(json)
            val jsonDecoder = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            val response = jsonDecoder.decodeFromString<T>(json)

            contentResponse.content = response
        } catch (ex: Exception) {
            val error = ErrorResponse()
            error.message = ex.message.toString()
            contentResponse.errorResponse = error
            print(ex.message.toString())
        }
        return contentResponse
    }
}