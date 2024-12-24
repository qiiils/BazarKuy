package com.example.bazarkuy.data.remote.retrofit

import android.content.Context
import com.example.bazarkuy.BuildConfig
import com.example.bazarkuy.data.local.UserPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    fun getApiService(context: Context): ApiService {
        // Logging interceptor untuk debugging
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


        // Mengambil token dari UserPreferences secara aman
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()

                // Ambil token dari UserPreferences
                val token = runBlocking {
                    UserPreferences().getToken(context)
                }

                // Tambahkan header Authorization jika token ada
                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", "Bearer ${token ?: ""}")
                    .method(original.method, original.body)

                chain.proceed(requestBuilder.build())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        // Bangun Retrofit instance
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
