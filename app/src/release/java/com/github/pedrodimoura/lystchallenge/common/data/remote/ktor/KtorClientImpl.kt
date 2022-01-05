package com.github.pedrodimoura.lystchallenge.common.data.remote.ktor

import com.github.pedrodimoura.lystchallenge.BuildConfig
import com.github.pedrodimoura.lystchallenge.common.data.remote.HttpClientProvider
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

private const val CONNECT_TIMEOUT = 60_000
private const val SOCKET_TIMEOUT = 60_000

private const val API_KEY_HEADER = "x-api-key"

class KtorClientImpl(
    private val connectTimeout: Int = CONNECT_TIMEOUT,
    private val socketTimeout: Int = SOCKET_TIMEOUT,
    private val apiKey: String = BuildConfig.API_KEY
) : HttpClientProvider.KtorClient {

    private val ktorHttpClient: HttpClient by lazy {
        HttpClient(Android) {
            engine {
                connectTimeout = this@KtorClientImpl.connectTimeout
                socketTimeout = this@KtorClientImpl.socketTimeout
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(API_KEY_HEADER, apiKey)
            }
        }
    }

    override fun getClient(): HttpClient = ktorHttpClient

}