package com.example.bazarkuy.ui.BazarDetail

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.remote.response.BazarDetailResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BazarDetailViewModel : ViewModel() {
    private val _bazarDetail = mutableStateOf<BazarDetailResponse?>(null)
    val bazarDetail: State<BazarDetailResponse?> = _bazarDetail

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun fetchBazarDetail(bazarId: Int, context: Context) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val apiService = ApiConfig.getApiService(context)
                val response = ApiService.getBazarDetail(bazarId)
                _bazarDetail.value = response
            } catch (e: HttpException) {
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}