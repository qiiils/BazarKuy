package com.example.bazarkuy.ui.AddBazarScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.bazarkuy.data.remote.retrofit.ApiService
import com.example.bazarkuy.data.remote.response.BazaarRequest
import com.example.bazarkuy.data.remote.response.BazarResponse
//import com.example.bazarkuy.data.remote.response.BaseResponse
//import com.example.bazarkuy.data.remote.response.BazaarRequest
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class AddBazaarViewModel(private val apiService: ApiService) : ViewModel() {

    // State for form fields
    val deskripsiAcara = MutableStateFlow("")
    val namaAcara = MutableStateFlow("")
    val startEventDate = MutableStateFlow("")
    val endEventDate = MutableStateFlow("")
    val registrationStartDate = MutableStateFlow("")
    val registrationEndDate = MutableStateFlow("")
    val lokasi = MutableStateFlow("")
    val termsAndConditions = MutableStateFlow("")

    // Submission state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _createdBazaar = MutableStateFlow<BazarResponse?>(null)
    val createdBazaar: StateFlow<BazarResponse?> = _createdBazaar

    // Submit data
    fun submitBazaar(token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _createdBazaar.value = null

            val request = BazaarRequest(
                name = namaAcara.value,
                description = deskripsiAcara.value,
                startEventDate = startEventDate.value,
                endEventDate = endEventDate.value,
                registrationStartDate = registrationStartDate.value,
                registrationEndDate = registrationEndDate.value,
                location = lokasi.value,
                termsAndConditions = termsAndConditions.value
            )

            try {
                // Log token untuk memastikan dikirimkan dengan benar
                println("Token: $token")
                val response = apiService.createBazaar("Bearer $token", request)
                if (response.isSuccessful && response.body() != null) {
                    _createdBazaar.value = response.body()
                } else {
                    _errorMessage.value = response.errorBody()?.string() ?: "Unknown error"
                }
            } catch (e: HttpException) {
                _errorMessage.value = "HTTP Error: ${e.message}"
            } catch (e: IOException) {
                _errorMessage.value = "Network Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}




