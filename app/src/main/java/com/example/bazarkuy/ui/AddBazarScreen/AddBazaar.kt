package com.example.bazarkuy.ui.AddBazarScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.lifecycle.ViewModelProvider
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.R
import com.example.bazarkuy.data.local.UserPreferences
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.runBlocking
import java.util.*
import android.app.DatePickerDialog
import java.util.Calendar

class AddBazaar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val apiService = ApiConfig.getApiService(this) // Dapatkan instance ApiService
            val viewModel: AddBazaarViewModel = ViewModelProvider(
                this,
                AddBazaarViewModelFactory.provideFactory(apiService)
            )[AddBazaarViewModel::class.java]

            // Dapatkan token dari UserPreferences
            val userPreferences = UserPreferences()
            val token = runBlocking { userPreferences.getToken(this@AddBazaar) }


            val navController = rememberNavController()
            DetailApply(navController = navController, viewModel = viewModel, token = token)
        }
    }
}

@Composable
fun DetailApply(navController: NavController, viewModel: AddBazaarViewModel, token: String) {
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val createdBazaar by viewModel.createdBazaar.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                title = { Text("Create Bazaar", fontSize = 20.sp) }
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
            // Nama Acara
            LabeledTextField(
                value = viewModel.namaAcara.collectAsState().value,
                onValueChange = { viewModel.namaAcara.value = it },
                label = "Nama Acara",
                placeholder = "Enter event name"
            )

            // Deskripsi Acara
            LabeledTextField(
                value = viewModel.deskripsiAcara.collectAsState().value,
                onValueChange = { viewModel.deskripsiAcara.value = it },
                label = "Deskripsi Acara",
                placeholder = "Enter event description"
            )

            // Start Event Date
            DatePickerField(
                label = "Start Event Date",
                value = viewModel.startEventDate.collectAsState().value,
                onValueChange = { viewModel.startEventDate.value = it }
            )

            // End Event Date
            DatePickerField(
                label = "End Event Date",
                value = viewModel.endEventDate.collectAsState().value,
                onValueChange = { viewModel.endEventDate.value = it }
            )

            // Registration Start Date
            DatePickerField(
                label = "Registration Start Date",
                value = viewModel.registrationStartDate.collectAsState().value,
                onValueChange = { viewModel.registrationStartDate.value = it }
            )

            // Registration End Date
            DatePickerField(
                label = "Registration End Date",
                value = viewModel.registrationEndDate.collectAsState().value,
                onValueChange = { viewModel.registrationEndDate.value = it }
            )

            // Lokasi
            LabeledTextField(
                value = viewModel.lokasi.collectAsState().value,
                onValueChange = { viewModel.lokasi.value = it },
                label = "Lokasi",
                placeholder = "Enter location"
            )

            // Terms and Conditions
            LabeledTextField(
                value = viewModel.termsAndConditions.collectAsState().value,
                onValueChange = { viewModel.termsAndConditions.value = it },
                label = "Terms and Conditions",
                placeholder = "Enter terms and conditions"
            )

            // Error message
            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "Unknown error",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Success message
            if (createdBazaar != null) {
                Text(
                    text = "Bazaar successfully created: ${createdBazaar!!.name}",
                    color = Color.Green,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Submit Button
            Button(
                onClick = { viewModel.submitBazaar(token) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6C63FF)),
                shape = RoundedCornerShape(8.dp),
                enabled = !isLoading
            ) {
                Text("Create Bazaar", color = Color.White)
            }

            // Loading indicator
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun DatePickerField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) } // Kontrol visibilitas dialog date picker

    // Menampilkan dialog jika showDatePicker bernilai true
    if (showDatePicker) {
        val context = LocalContext.current
        val calendar = Calendar.getInstance()
        android.app.DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Format tanggal menjadi DD-MM-YYYY
                val formattedDate = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year)
                onValueChange(formattedDate) // Mengirim tanggal yang dipilih
                showDatePicker = false // Menutup dialog setelah tanggal dipilih
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    // Field input dengan action klik untuk memicu DatePickerDialog
    OutlinedTextField(
        value = value,
        onValueChange = { }, // Tidak ada perubahan langsung karena hanya read-only
        label = { Text(label) },
        placeholder = { Text("Select a date") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { showDatePicker = true }, // Tampilkan dialog saat field diklik
        readOnly = true // Membuat input hanya bisa dipilih melalui dialog
    )
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
