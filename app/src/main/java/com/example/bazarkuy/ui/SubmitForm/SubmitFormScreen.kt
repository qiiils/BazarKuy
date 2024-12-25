package com.example.bazarkuy.ui.submitform

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.response.ApplyRequest
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.ui.SubmitForm.SubmitFormViewModelFactory
import kotlinx.coroutines.launch
import com.example.bazarkuy.ui.SubmitForm.SubmitFormViewModel// Sesuaikan dengan package yang benar


class SubmitFormScreen : ComponentActivity() {
    private lateinit var viewModel: SubmitFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            // Check role first
            lifecycleScope.launch {
                val userRole = UserPreferences().getRole(applicationContext)
                if (userRole != "umkm") {
                    Toast.makeText(
                        this@SubmitFormScreen,
                        "Hanya UMKM yang dapat mendaftar",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                    return@launch
                }
            }
            // Initialize ViewModel
            viewModel = ViewModelProvider(
                this,
                SubmitFormViewModelFactory(
                    apiService = ApiConfig.getApiService(applicationContext),
                    application = application
                )
            )[SubmitFormViewModel::class.java]

            // Get bazarId from intent
            val bazarId = intent.getIntExtra("bazarId", -1)
            if (bazarId == -1) {
                Toast.makeText(this, "Invalid Bazar ID", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

            setContent {
                MaterialTheme {
                    SubmitFormContent(
                        bazarId = bazarId,
                        viewModel = viewModel,
                        onBackPress = { finish() }
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("SubmitFormScreen", "Error initializing screen", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

@Composable
fun SubmitFormContent(
    bazarId: Int,
    viewModel: SubmitFormViewModel,
    onBackPress: () -> Unit
) {
    var businessName by remember { mutableStateOf("") }
    var businessDescription by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val isLoading by viewModel.isLoading.collectAsState()

    Column {
        TopAppBar(
            title = { Text("Submit Form") },
            navigationIcon = {
                IconButton(onClick = onBackPress) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            backgroundColor = Color(0xFF6200EE),
            contentColor = Color.White
        )

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = businessName,
                onValueChange = { businessName = it },
                label = { Text("Nama Usaha") },
                placeholder = { Text("Masukkan nama usaha Anda") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = businessDescription,
                onValueChange = { businessDescription = it },
                label = { Text("Deskripsi Usaha") },
                placeholder = { Text("Masukkan deskripsi usaha Anda") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)  // Made taller for description
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(8.dp),
                maxLines = 4
            )

            Button(
                onClick = {
                    if (businessName.isEmpty() || businessDescription.isEmpty()) {
                        Toast.makeText(context, "Mohon isi semua field", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    scope.launch {
                        try {
                            val request = ApplyRequest(
                                businessName = businessName,
                                businessDescription = businessDescription
                            )
                            viewModel.submitApplication(bazarId, request)
                            Toast.makeText(context, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show()
                            onBackPress()
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Error: ${e.message ?: "Unknown error"}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                shape = RoundedCornerShape(8.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text("Daftar Sekarang", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}