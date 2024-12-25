package com.example.bazarkuy.ui.SubmitForm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazarkuy.data.remote.retrofit.ApiService
import com.example.bazarkuy.ui.SubmitForm.SubmitFormViewModel

class SubmitFormViewModelFactory(
    private val apiService: ApiService,
    private val application: Application  // Tambahkan ini
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubmitFormViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SubmitFormViewModel(apiService, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}