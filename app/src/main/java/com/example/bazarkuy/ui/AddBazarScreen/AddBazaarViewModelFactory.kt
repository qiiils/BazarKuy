package com.example.bazarkuy.ui.AddBazarScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazarkuy.data.remote.retrofit.ApiService

class AddBazaarViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBazaarViewModel::class.java)) {
            return AddBazaarViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        fun provideFactory(apiService: ApiService): AddBazaarViewModelFactory {
            return AddBazaarViewModelFactory(apiService)
        }
    }
}
