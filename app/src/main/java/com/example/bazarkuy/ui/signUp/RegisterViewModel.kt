package com.example.bazarkuy.ui.signUp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.response.RegisterRequest
import com.example.bazarkuy.data.remote.response.RegisterResponse
//import com.example.bazarkuy.data.remote.response.UserRoles
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val context: Context) : ViewModel() {
    companion object {
        const val ROLE_UMKM = "umkm"
        const val ROLE_PENYELENGGARA = "Penyelenggara Bazar"
    }
    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState> = _registerState

    fun register(email: String, name: String, password: String, role: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                // Log role yang diterima
                // Log role yang diterima
                Log.d("RegisterDebug", "Role yang diterima: $role")

// Pastikan format role sesuai
                val formattedRole = when (role.lowercase()) {
                    "umkm" -> ROLE_UMKM
                    "penyelenggara bazar" -> ROLE_PENYELENGGARA
                    else -> role
                }

                Log.d("RegisterDebug", "Role setelah format: $formattedRole")

                val request = RegisterRequest(email, name, password, formattedRole)
                val apiService = ApiConfig.getApiService(context)
                val response = apiService.register(request)

                if (response.isSuccessful) {
                    // Simpan role setelah register berhasil
                    val userPreferences = UserPreferences()

                    Log.d("RegisterDebug", "Menyimpan role: $formattedRole")
                    userPreferences.saveRole(context, formattedRole)
                    userPreferences.saveLoginState(context, true)

                    // Simpan token jika ada
                    response.body()?.token?.let { token ->
                        Log.d("RegisterDebug", "Token tersimpan")
                        userPreferences.saveToken(context, token)
                    }

                    _registerState.value = RegisterState.Success(response.body()!!)
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("RegisterDebug", "Register gagal: $errorMessage")
                    _registerState.value = RegisterState.Error(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("RegisterDebug", "Error saat register", e)
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