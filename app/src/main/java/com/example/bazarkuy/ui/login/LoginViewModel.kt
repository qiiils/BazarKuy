package com.example.bazarkuy.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.data.remote.response.LoginRequest
import com.example.bazarkuy.data.remote.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = ApiConfig.apiService.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val loginResponse = response.body()!! // Ambil respons dari API
                    _loginState.value = LoginState.Success(loginResponse)
                } else {
                    _loginState.value = LoginState.Error(response.message())
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("An error occurred: ${e.message}")
            }
        }
    }
}

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val data: LoginResponse) : LoginState() // Gunakan LoginResponse
    data class Error(val message: String) : LoginState()
}
