import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.response.BazarResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.awaitResponse

class DashboardViewModel(private val context: Context) : ViewModel() {
    private lateinit var apiService: ApiService
    private val _ongoingBazaars = mutableStateOf<List<BazarResponse>>(emptyList())
    val ongoingBazaars: State<List<BazarResponse>> = _ongoingBazaars
    val token = runBlocking { UserPreferences().getToken(context) ?: "" }

    private val _comingSoonBazaars = mutableStateOf<List<BazarResponse>>(emptyList())
    val comingSoonBazaars: State<List<BazarResponse>> = _comingSoonBazaars

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _applyNowBazaars = mutableStateOf<List<BazarResponse>>(emptyList())
    val applyNowBazaars: State<List<BazarResponse>> = _applyNowBazaars

    init {
        viewModelScope.launch {
            apiService = ApiConfig.getApiService(context)
        }
    }

    fun fetchBazaars() {
        viewModelScope.launch {
            try {
                val ongoingResponse = apiService.getOngoingBazaars(token = token)
                if (ongoingResponse.isSuccessful) {
                    _ongoingBazaars.value = ongoingResponse.body() ?: emptyList()
                } else {
                    _error.value = "Failed to fetch ongoing bazaars: ${ongoingResponse.code()}"
                }

                val comingSoonResponse = apiService.getComingSoonBazaars(token = token)
                if (comingSoonResponse.isSuccessful) {
                    _comingSoonBazaars.value = comingSoonResponse.body() ?: emptyList()
                } else {
                    _error.value = "Failed to fetch upcoming bazaars: ${comingSoonResponse.code()}"
                }

                val applyNowResponse = apiService.getOpenBazaars(token = token)
                if (applyNowResponse.isSuccessful) {
                    _applyNowBazaars.value = applyNowResponse.body() ?: emptyList()
                }
            } catch (e: Exception) {
                _error.value = "Network error: ${e.message}"
            }
        }
    }
}

class DashboardViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(context) as T
    }
}