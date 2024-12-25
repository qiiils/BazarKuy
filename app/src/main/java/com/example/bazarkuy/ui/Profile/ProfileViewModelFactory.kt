package com.example.bazarkuy.ui.Profile

import ProfileViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazarkuy.data.remote.retrofit.ApiService

class ProfileViewModelFactory(
    private val context: Context,
    private val apiService: ApiService
) : ViewModelProvider.Factory {

    companion object {
        fun provideFactory(
            context: Context,
            apiService: ApiService
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProfileViewModel(context, apiService) as T
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(context, apiService) as T
    }
}