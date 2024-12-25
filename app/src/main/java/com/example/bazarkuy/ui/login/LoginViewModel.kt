import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.response.LoginRequest
import com.example.bazarkuy.data.remote.response.LoginResponse
//import com.example.bazarkuy.data.remote.response.UserRoles
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState
    private val userPreferences = UserPreferences()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginState.value = LoginState.Loading
                val response = ApiConfig.getApiService(getApplication()).login(
                    LoginRequest(email, password)
                )

                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        userPreferences.saveToken(getApplication(), loginResponse.token ?: "")
                        _loginState.value = LoginState.Success(loginResponse)
                    }
                } else {
                    _loginState.value = LoginState.Error(response.message())
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
sealed class LoginState {
    object Loading : LoginState()
    data class Success(val data: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}