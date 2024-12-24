package com.example.bazarkuy.ui.BazarDetail

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.response.BazarDetailResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

class BazarDetailViewModel(private val context: Context) : ViewModel() {
    private lateinit var apiService: ApiService
    val token = runBlocking { UserPreferences().getToken(context) ?: "" }
    private val _bazarDetail = mutableStateOf<BazarDetailResponse?>(null)
    val bazarDetail: State<BazarDetailResponse?> = _bazarDetail

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        viewModelScope.launch {
            apiService = ApiConfig.getApiService(context)
        }
    }

    // Fungsi untuk mengambil data detail bazar
    fun fetchBazarDetail(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Buat instance ApiService melalui ApiConfig
                val apiService = ApiConfig.getApiService(context)

                // Panggil endpoint getBazarDetail
                val response = apiService.getBazarDetail(id, token = token)
                if (response.isSuccessful) {
                    _bazarDetail.value = response.body()
                    _error.value = null
                } else {
                    _error.value = "Error: ${response.message()}"
                }
            } catch (e: HttpException) {
                _error.value = "HTTP Error: ${e.message}"
            } catch (e: Exception) {
                _error.value = "Unexpected Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

// Factory untuk membuat ViewModel dengan Context
class BazarDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BazarDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BazarDetailViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
