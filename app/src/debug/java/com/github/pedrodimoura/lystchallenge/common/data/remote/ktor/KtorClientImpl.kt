package com.github.pedrodimoura.lystchallenge.common.data.remote.ktor

import android.util.Log
import com.github.pedrodimoura.lystchallenge.BuildConfig
import com.github.pedrodimoura.lystchallenge.common.data.remote.HttpClientProvider
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

private const val CONNECT_TIMEOUT = 60_000
private const val SOCKET_TIMEOUT = 60_000

private const val RESPONSE_LOG_TAG = "HTTP status"
private const val API_KEY_HEADER = "x-api-key"

class KtorClientImpl(
    private val connectTimeout: Int = CONNECT_TIMEOUT,
    private val socketTimeout: Int = SOCKET_TIMEOUT,
    private val apiKey: String = BuildConfig.API_KEY
) : HttpClientProvider.KtorClient {

    private val ktorHttpClient: HttpClient by lazy {
        HttpClient(Android) {
            engine {
                connectTimeout = CONNECT_TIMEOUT
                socketTimeout = SOCKET_TIMEOUT
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("Ktor Logging ->", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d(RESPONSE_LOG_TAG, "${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(API_KEY_HEADER, BuildConfig.API_KEY)
            }
        }
    }

    override fun getClient(): HttpClient = ktorHttpClient

}