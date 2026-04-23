package com.example.newsapp_jetpackcompose.core.di

import com.example.newsapp_jetpackcompose.BuildConfig
import com.example.newsapp_jetpackcompose.data.service.remote.service.ApiService
import com.example.newsapp_jetpackcompose.data.service.remote.service.KtorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
        isLenient = true
        prettyPrint = true
    }

    @Singleton
    @Provides
    fun provideHttp(json: Json): HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            level = LogLevel.BODY
        }
        install(DefaultRequest) {
            header("X-Api-Key", BuildConfig.API_KEY)
            header(HttpHeaders.Accept, "application/json")
        }
    }

    @Singleton
    @Provides
    fun provideApiService(
        httpClient: HttpClient
    ): ApiService = KtorService(httpClient)

    @Provides
    @Named("api_key")
    fun provideApiKey(): String = BuildConfig.API_KEY
}