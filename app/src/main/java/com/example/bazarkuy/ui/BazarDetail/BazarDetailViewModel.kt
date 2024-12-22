package com.example.bazarkuy.ui.bazardetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.remote.response.BazarDetailResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch

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
            _error.value = null
            try {
                val response = ApiConfig.apiService.getBazarDetail(bazarId)
                println("Response code: ${response.code()}")
                println("Response body: ${response.body()}")

                if (response.isSuccessful) {
                    _bazarDetail.value = response.body()
                } else {
                    _error.value = "Gagal mengambil detail bazar: ${response.code()}"
                    println("Error body: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.value = "Terjadi kesalahan: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}