package com.example.bazarkuy.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences {
    private val tokenKey = stringPreferencesKey("auth_token")
    private val userIdKey = stringPreferencesKey("user_id")
    private val isLoggedInKey = stringPreferencesKey("is_logged_in")

    suspend fun saveLoginState(context: Context, isLoggedIn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[isLoggedInKey] = isLoggedIn.toString()
        }
    }

    suspend fun getLoginState(context: Context): Boolean {
        return context.dataStore.data.first()[isLoggedInKey]?.toBoolean() ?: false
    }

    suspend fun saveUserId(context: Context, userId: Int) {
        context.dataStore.edit { prefs ->
            prefs[userIdKey] = userId.toString()
        }
    }

    suspend fun saveToken(context: Context, token: String) {
        context.dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    suspend fun getToken(context: Context): String? {
        val token = context.dataStore.data.first()[tokenKey]
        Log.d("UserPreferences", "Token yang diambil: $token")
        return token
    }

    suspend fun getUserId(context: Context): Int {
        val userId = context.dataStore.data.first()[userIdKey]?.toInt() ?: 0
        Log.d("UserPreferences", "User ID yang diambil: $userId")
        return userId
    }

    suspend fun logout(context: Context) {
        context.dataStore.edit { prefs ->
            prefs[isLoggedInKey] = false.toString() // Mengatur login state ke false
            prefs[userIdKey] = "" // Kosongkan user ID
            prefs[tokenKey] = "" // Kosongkan token
        }
    }
}