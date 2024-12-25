package com.example.bazarkuy.ui.AddBazarScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.R
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.ui.login.LoginViewModelFactory

class AddBazaar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val apiService = ApiConfig.getApiService(this) // Dapatkan instance ApiService
            val viewModel: AddBazaarViewModel = ViewModelProvider(
                this,
                AddBazaarViewModelFactory.provideFactory(apiService)
            )[AddBazaarViewModel::class.java]

            val navController = rememberNavController()
            DetailApply(navController = navController, viewModel = viewModel)
        }
    }
}
    @Composable
    fun DetailApply(navController: NavController, viewModel: AddBazaarViewModel) {
        // Previous implementation remains the same
        val deskripsiAcara by viewModel.deskripsiAcara.collectAsState()
        val namaAcara by viewModel.namaAcara.collectAsState()
        val tanggalPelaksanaan by viewModel.tanggalPelaksanaan.collectAsState()
        val lokasi by viewModel.lokasi.collectAsState()
        val tema by viewModel.tema.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()
        val errorMessage by viewModel.errorMessage.collectAsState()
        val isSuccess by viewModel.isSuccess.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    elevation = 0.dp,
                    navigationIcon = {
                        IconButton(onClick = { /* TODO: Handle back button click */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center // Menempatkan konten di tengah
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top // Mengatur agar konten berada di atas
                            ) {
                                Spacer(modifier = Modifier.height(16.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                // Menambahkan jarak di atas judul
                                Text("FORM", fontSize = 24.sp)
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(paddingValues)
            ) {
                LabeledTextField(
                    value = deskripsiAcara,
                    onValueChange = { deskripsiAcara = it },
                    label = "Deskripsi Acara",
                    placeholder = "Enter the event description"
                )
                LabeledTextField(
                    value = namaAcara,
                    onValueChange = { namaAcara = it },
                    label = "Nama Acara",
                    placeholder = "Enter the event name"
                )
                LabeledTextField(
                    value = tanggalPelaksanaan,
                    onValueChange = { tanggalPelaksanaan = it },
                    label = "Tanggal Pelaksanaan",
                    placeholder = "Enter the event date"
                )
                LabeledTextField(
                    value = lokasi,
                    onValueChange = { lokasi = it },
                    label = "Lokasi",
                    placeholder = "Enter the event location"
                )
                LabeledTextField(
                    value = tema,
                    onValueChange = { tema = it },
                    label = "Tema",
                    placeholder = "Enter the event theme"
                )

                // Upload Fields
                UploadField(label = "Upload Icon (1000 × 1000)")
                UploadField(label = "Upload Header (2000 × 1000)")

                // Submit Button
                Button(
                    onClick = { /* Handle form submission */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6C63FF)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Upload", color = Color.White)
                }
            }
        }
    }

@Composable
fun LabeledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        singleLine = true
    )
}

@Composable
fun UploadField(label: String) {
    Text(
        text = label,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
        color = Color.Gray
    )
    OutlinedTextField(
        value = "",
        onValueChange = { },
        placeholder = { Text("Upload from your device...", color = Color.LightGray) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Info",
                tint = Color.Gray
            )
        },
        enabled = false
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailApplyPreview() {
    val navController = rememberNavController()
    val dummyViewModel = AddBazaarViewModel(ApiConfig.getApiService(LocalContext.current))
    DetailApply(navController = navController, viewModel = dummyViewModel)
}