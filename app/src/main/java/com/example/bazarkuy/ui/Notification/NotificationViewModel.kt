package com.example.bazarkuy.ui.Notification

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.response.NotificationResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import  com.example.bazarkuy.data.remote.retrofit.ApiService

class NotificationViewModel(
    private val apiService: ApiService,
    application: Application
) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences()

    private val _notifications = MutableStateFlow<List<NotificationResponse>>(emptyList())
    val notifications = _notifications.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun fetchNotifications() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = userPreferences.getToken(getApplication())
                val response = apiService.getNotifications("Bearer $token")

                if (response.isSuccessful) {
                    response.body()?.let { notifications ->
                        _notifications.value = notifications
                    }
                } else {
                    _error.value = "Failed to fetch notifications"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}