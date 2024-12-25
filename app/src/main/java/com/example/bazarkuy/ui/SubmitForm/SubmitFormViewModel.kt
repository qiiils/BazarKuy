package com.example.bazarkuy.ui.SubmitForm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.response.ApplyRequest
//import com.example.bazarkuy.data.remote.response.UserRoles
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.bazarkuy.data.remote.response.User
class SubmitFormViewModel(
    private val apiService: ApiService,
    application: Application
) : AndroidViewModel(application) {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val userPreferences = UserPreferences()

    companion object {
        private const val ROLE_UMKM = "umkm"
    }

    suspend fun submitApplication(bazarId: Int, request: ApplyRequest) {
        _isLoading.value = true
        _error.value = null

        try {
            val userRole = userPreferences.getRole(getApplication())
            Log.d("SubmitDebug", "Current Role: $userRole") // Tambahkan ini
            Log.d("SubmitDebug", "Role Type: ${userRole?.javaClass?.name}") // Cek tipe data role
            Log.d("SubmitDebug", "Role Length: ${userRole?.length}") // Cek panjang string role

            if (userRole == null || !userRole.equals(ROLE_UMKM, ignoreCase = true)) {
                Log.e("SubmitDebug", "Role tidak valid: '$userRole'")
                throw Exception("Hanya UMKM yang dapat mendaftar")
            }
            // Get token
            val token = UserPreferences().getToken(getApplication())
            if (token.isNullOrEmpty()) {
                throw Exception("Silakan login terlebih dahulu")
            }

            Log.d("SubmitForm", "Submitting application...")

            val response = apiService.applyToBazar(
                bazarId = bazarId,
                token = "Bearer $token",
                request = request
            )

            if (!response.isSuccessful) {
                val errorBody = response.errorBody()?.string()
                Log.e("SubmitForm", "API Error: ${response.code()} - $errorBody")
                throw Exception(errorBody ?: "Terjadi kesalahan saat mendaftar")
            }

            val responseBody = response.body()
            if (responseBody == null) {
                throw Exception("Tidak ada response dari server")
            }

            Log.d("SubmitForm", "Submit successful: ${responseBody.id}")
        } catch (e: Exception) {
            Log.e("SubmitForm", "Error in submitApplication", e)
            _error.value = e.message ?: "Terjadi kesalahan"
            throw e
        } finally {
            _isLoading.value = false
        }
    }
}