package com.example.bazarkuy.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.bazarkuy.BuildConfig.BASE_URL
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences {
    private val roleKey = stringPreferencesKey("user_role")
    private val tokenKey = stringPreferencesKey("auth_token")
    private val userIdKey = stringPreferencesKey("user_id")
    private val isLoggedInKey = stringPreferencesKey("is_logged_in")

    suspend fun saveLoginState(context: Context, isLoggedIn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[isLoggedInKey] = isLoggedIn.toString()
        }
    }

    suspend fun getApiService(context: Context): ApiService {
        val token = getToken(context)
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    suspend fun saveUserId(context: Context, userId: Int) {
        context.dataStore.edit { prefs ->
            prefs[userIdKey] = userId.toString()
        }
    }

    suspend fun saveToken(context: Context, token: String) {
        Log.d("TokenDebug", "Saving token: ${token.take(10)}...")
        context.dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    suspend fun getToken(context: Context): String {
        val token = context.dataStore.data.first()[tokenKey] ?: ""
        Log.d("TokenDebug", "Retrieved token: ${token.take(10)}...")
        return token
    }

    suspend fun getUserId(context: Context): Int {
        val userId = context.dataStore.data.first()[userIdKey]?.toInt() ?: 0
        Log.d("UserPreferences", "User ID yang diambil: $userId")
        return userId
    }

    suspend fun saveRole(context: Context, role: String) {
        Log.d("RoleDebug", "Saving role: '$role'")
        context.dataStore.edit { prefs ->
            prefs[roleKey] = role  // Simpan tanpa mengubah format
        }
    }

    suspend fun getRole(context: Context): String? {
        val role = context.dataStore.data.first()[roleKey]
        Log.d("RoleDebug", "Retrieved role: '$role'")
        return role
    }

    suspend fun logout(context: Context) {
        context.dataStore.edit { prefs ->
            prefs[isLoggedInKey] = false.toString()
            prefs[userIdKey] = ""
            prefs[tokenKey] = ""
            prefs[roleKey] = "" // Tambahkan ini untuk membersihkan role saat logout
        }
        Log.d("LogoutDebug", "User logged out, all preferences cleared")
    }
}