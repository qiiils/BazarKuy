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
    private val _deskripsiAcara = MutableStateFlow("")
    val deskripsiAcara: StateFlow<String> = _deskripsiAcara

    private val _namaAcara = MutableStateFlow("")
    val namaAcara: StateFlow<String> = _namaAcara

    private val _tanggalPelaksanaan = MutableStateFlow("")
    val tanggalPelaksanaan: StateFlow<String> = _tanggalPelaksanaan

    private val _lokasi = MutableStateFlow("")
    val lokasi: StateFlow<String> = _lokasi

    private val _tema = MutableStateFlow("")
    val tema: StateFlow<String> = _tema

    // State for submission
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    // Update field states
    fun updateDeskripsiAcara(value: String) {
        _deskripsiAcara.value = value
    }

    fun updateNamaAcara(value: String) {
        _namaAcara.value = value
    }

    fun updateTanggalPelaksanaan(value: String) {
        _tanggalPelaksanaan.value = value
    }

    fun updateLokasi(value: String) {
        _lokasi.value = value
    }

    fun updateTema(value: String) {
        _tema.value = value
    }

    // Submit form data to API
    fun submitBazaar() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _isSuccess.value = false

            val request = BazaarRequest(
                deskripsiAcara = _deskripsiAcara.value,
                namaAcara = _namaAcara.value,
                tanggalPelaksanaan = _tanggalPelaksanaan.value,
                lokasi = _lokasi.value,
                tema = _tema.value
            )

            try {
                val token = "Bearer ${getStoredToken()}" // Ambil token dari penyimpanan
                val response: Response<String> = ApiService.createBazaar(token, request)

                if (response.isSuccessful) {
                    _isSuccess.value = true
                } else {
                    _errorMessage.value =
                        response.errorBody()?.string() ?: "Failed to create bazaar"
                }
            } catch (e: HttpException) {
                _errorMessage.value = "Failed to submit: ${e.message()}"
            } catch (e: IOException) {
                _errorMessage.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

fun createBazaar() {
    viewModelScope.launch {
        _isLoading.value = true
        _errorMessage.value = null

        val request = BazaarRequest(
            deskripsiAcara = _deskripsiAcara.value,
            namaAcara = _namaAcara.value,
            tanggalPelaksanaan = _tanggalPelaksanaan.value,
            lokasi = _lokasi.value,
            tema = _tema.value
        )

        try {
            val token = "Bearer ${getStoredToken()}"
            val response = ApiService.createBazaar(token, request)

            if (response.isSuccessful && response.body() != null) {
                _isSuccess.value = true
            } else {
                _errorMessage.value = response.errorBody()?.string() ?: "Failed to create bazaar"
            }
        } catch (e: HttpException) {
            _errorMessage.value = "Failed to submit: ${e.message()}"
        } catch (e: IOException) {
            _errorMessage.value = "Network error: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }
}


