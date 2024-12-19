package com.example.bazarkuy.ui.signUp

import android.util.Log
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

class RegisterViewModel : ViewModel() {
    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState> = _registerState

    fun register(email: String, name: String, password: String, role: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                val request = RegisterRequest(email, name, password, role)
                Log.d("RegisterViewModel", "Request: $request") // Log request

                val response = ApiConfig.apiService.register(request)
                if (response.isSuccessful) {
                    Log.d("RegisterViewModel", "Response: ${response.body()}") // Log respons sukses
                    _registerState.value = RegisterState.Success(response.body()!!)
                } else {
                    Log.e(
                        "RegisterViewModel",
                        "Error: ${response.errorBody()?.string()}"
                    ) // Log respons error
                    _registerState.value = RegisterState.Error(response.message())
                }
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Exception: ${e.localizedMessage}") // Log exception
                _registerState.value = RegisterState.Error("An error occurred")
            }
        }
    }
}
sealed class RegisterState {
    object Loading : RegisterState()
    data class Success(val data: RegisterResponse) : RegisterState()
    data class Error(val message: String) : RegisterState()
}