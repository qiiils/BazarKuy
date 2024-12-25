package com.example.bazarkuy.ui.Notification

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazarkuy.data.remote.retrofit.ApiService

class NotificationViewModelFactory(
    private val apiService: ApiService,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotificationViewModel(apiService, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}