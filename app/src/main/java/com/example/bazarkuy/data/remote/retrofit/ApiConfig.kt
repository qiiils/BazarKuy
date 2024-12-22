package com.example.bazarkuy.data.remote.retrofit

import com.example.bazarkuy.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private val client = OkHttpClient.Builder()
        .connectTimeout(600, TimeUnit.SECONDS) // Timeout koneksi
        .readTimeout(600, TimeUnit.SECONDS)    // Timeout membaca data
        .writeTimeout(600, TimeUnit.SECONDS)   // Timeout menulis data
        .build()

    private const val BASE_URL = BuildConfig.BASE_URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}