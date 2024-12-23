package com.example.bazarkuy.ui.bazardetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.remote.response.BazarDetailResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BazarDetailViewModel : ViewModel() {
    private val _bazarDetail = mutableStateOf<BazarDetailResponse?>(null)
    val bazarDetail: State<BazarDetailResponse?> = _bazarDetail

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun fetchBazarDetail(bazarId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                println("Fetching bazar detail for ID: $bazarId") // Debug log
                val response = ApiConfig.apiService.getBazarDetail(bazarId)
                println("Response received: $response") // Debug log
                _bazarDetail.value = response
                _error.value = null
            } catch (e: HttpException) {
                println("HTTP Error: ${e.code()} - ${e.message()}") // Debug log
                when (e.code()) {
                    401 -> _error.value = "Unauthorized: Silakan login kembali"
                    403 -> _error.value = "Forbidden: Anda tidak memiliki akses"
                    404 -> _error.value = "Bazar tidak ditemukan"
                    else -> _error.value = "Error: ${e.message()}"
                }
            } catch (e: Exception) {
                println("General Error: ${e.message}") // Debug log
                _error.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}