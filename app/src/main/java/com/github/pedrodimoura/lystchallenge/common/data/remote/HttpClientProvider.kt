package com.github.pedrodimoura.lystchallenge.common.data.remote

import io.ktor.client.*

sealed interface HttpClientProvider {
    interface KtorClient : HttpClientProvider {
        fun getClient(): HttpClient
    }
}