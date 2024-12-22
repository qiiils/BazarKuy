import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.data.remote.response.BazarResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class DashboardViewModel : ViewModel() {
    private val _ongoingBazaars = mutableStateOf<List<BazarResponse>>(emptyList())
    val ongoingBazaars: State<List<BazarResponse>> = _ongoingBazaars

    private val _comingSoonBazaars = mutableStateOf<List<BazarResponse>>(emptyList())
    val comingSoonBazaars: State<List<BazarResponse>> = _comingSoonBazaars

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun fetchBazaars() {
        println("Mulai fetch bazaars")
        viewModelScope.launch {
            try {
                // Mengambil ongoing bazaars
                println("Mencoba mengambil ongoing bazaars...")
                val ongoingResponse = ApiConfig.apiService.getOngoingBazaars()
                println("Ongoing Response Code: ${ongoingResponse.code()}")
                println("Ongoing Response Body: ${ongoingResponse.body()}")

                if (ongoingResponse.isSuccessful) {
                    val bazaarList = ongoingResponse.body()
                    println("Ongoing Bazaars received: $bazaarList")
                    _ongoingBazaars.value = bazaarList ?: emptyList()
                } else {
                    println("Ongoing Error: ${ongoingResponse.errorBody()?.string()}")
                    _error.value = "Gagal mengambil data bazaar yang sedang berlangsung: ${ongoingResponse.code()}"
                }

                // Mengambil coming soon bazaars
                println("Mencoba mengambil coming soon bazaars...")
                val comingSoonResponse = ApiConfig.apiService.getComingSoonBazaars()
                println("Coming Soon Response Code: ${comingSoonResponse.code()}")
                println("Coming Soon Response Body: ${comingSoonResponse.body()}")

                if (comingSoonResponse.isSuccessful) {
                    val bazaarList = comingSoonResponse.body()
                    println("Coming Soon Bazaars received: $bazaarList")
                    _comingSoonBazaars.value = bazaarList ?: emptyList()
                } else {
                    println("Coming Soon Error: ${comingSoonResponse.errorBody()?.string()}")
                    _error.value = "Gagal mengambil data bazaar yang akan datang: ${comingSoonResponse.code()}"
                }
            } catch (e: Exception) {
                println("Network Error: ${e.message}")
                e.printStackTrace()
                _error.value = "Error jaringan: ${e.message}"
            }
        }
    }
}