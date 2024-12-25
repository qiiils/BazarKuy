package com.example.bazarkuy.ui.Tracker

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.remote.response.BazarResponse
import com.example.bazarkuy.data.remote.response.ApplyResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListProgressViewModel(private val context: Context) : ViewModel() {

    private val _bazaars = MutableStateFlow<List<BazarResponse>>(emptyList())
    val bazaars: StateFlow<List<BazarResponse>> = _bazaars

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchData(userRole: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val apiService = ApiConfig.getApiService(context)

                _bazaars.value = if (userRole == "umkm") {
                    val applications: List<ApplyResponse> = apiService.getApplicationHistory()
                    applications.map { application ->
                        BazarResponse(
                            id = application.bazarId.toInt(),
                            name = application.businessName,
                            description = application.businessDescription,
                            endEventDate = "",
                            registrationStartDate = "",
                            registrationEndDate = "",
                            location = "",
                            status = application.status,
                            startEventDate = "",
                            organizerId = 0
                        )
                    }
                } else {
                    apiService.getBazarHistory()
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Failed to fetch data: ${e.message}"
            }
        }
    }
}
