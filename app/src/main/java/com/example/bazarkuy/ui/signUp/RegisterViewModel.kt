package com.example.bazarkuy.ui.signUp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.remote.response.RegisterRequest
import com.example.bazarkuy.data.remote.response.RegisterResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val context: Context) : ViewModel() {
    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState> = _registerState

    fun register(email: String, name: String, password: String, role: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                val request = RegisterRequest(email, name, password, role)
                val apiService = ApiConfig.getApiService(context)
                val response = apiService.register(request)
                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success(response.body()!!)
                } else {
                    _registerState.value = RegisterState.Error(response.errorBody()?.string() ?: "Unknown error")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "An error occurred")
            }
        }
    }
}
sealed class RegisterState {
    object Loading : RegisterState()
    data class Success(val data: RegisterResponse) : RegisterState()
    data class Error(val message: String) : RegisterState()
}