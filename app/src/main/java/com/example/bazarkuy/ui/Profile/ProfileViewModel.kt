// ProfileViewModel.kt
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.bazarkuy.data.remote.response.ProfileResponse
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.asStateFlow
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.request.ChangePasswordRequest

class ProfileViewModel(
    private val context: Context,
    private val apiService: ApiService
) : ViewModel() {
    private val userPreferences = UserPreferences()

    private val _profile = MutableStateFlow<ProfileResponse?>(null)
    val profile = _profile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _isChangingPassword = MutableStateFlow(false)
    val isChangingPassword = _isChangingPassword.asStateFlow()

    private val _changePasswordError = MutableStateFlow<String?>(null)
    val changePasswordError = _changePasswordError.asStateFlow()

    fun fetchProfile() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val storedToken = userPreferences.getToken(context)
                Log.d("ProfileViewModel", "Token: Bearer $storedToken")

                if (storedToken.isBlank()) {
                    _error.value = "Token tidak valid"
                    return@launch
                }

                val token = "Bearer $storedToken"
                val response = apiService.getProfile(token)

                if (response.isSuccessful && response.body() != null) {
                    _profile.value = response.body()
                } else {
                    _error.value = "Gagal mengambil profil: ${response.message()}"
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error fetching profile", e)
                _error.value = e.message ?: "Terjadi kesalahan"
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun changePassword(currentPassword: String, newPassword: String) {
        viewModelScope.launch {
            try {
                _isChangingPassword.value = true
                _changePasswordError.value = null

                val token = "Bearer ${userPreferences.getToken(context)}"
                val response = apiService.changePassword(
                    token,
                    ChangePasswordRequest(currentPassword, newPassword)
                )

                if (response.isSuccessful) {
                    // Reset password fields setelah berhasil
                    _changePasswordError.value = null
                } else {
                    _changePasswordError.value = "Gagal mengubah password: ${response.message()}"
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error changing password", e)
                _changePasswordError.value = e.message ?: "Terjadi kesalahan"
            } finally {
                _isChangingPassword.value = false
            }
        }
    }
}