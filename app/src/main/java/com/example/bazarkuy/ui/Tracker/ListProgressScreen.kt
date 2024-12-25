package com.example.bazarkuy.ui.Tracker

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bazarkuy.R
import com.example.bazarkuy.data.remote.response.BazarResponse
import com.example.bazarkuy.data.remote.response.ApplyResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Composable
fun ListProgressScreen(navController: NavController, userRole: String, context: Context) {
    // Inisialisasi ViewModel
    val viewModel = remember { ListProgressViewModel(context) }
    val bazaars by viewModel.bazaars.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
// Dapatkan context dari Jetpack Compose
    val context = LocalContext.current

//    LaunchedEffect(Unit) {
//        try {
//            isLoading = true
//            val apiService = ApiConfig.getApiService(context) // Dapatkan instance ApiService
//
//            bazaars = if (userRole == "umkm") {
//                // Jika pengguna adalah UMKM, ambil riwayat aplikasi
//                val applications: List<ApplyResponse> = apiService.getApplicationHistory()
//                applications.map { application ->
//                    BazarResponse(
//                        id = application.bazarId.toInt(),
//                        name = application.businessName,
//                        description = application.businessDescription,
//                        endEventDate = "",
//                        registrationStartDate = "",
//                        registrationEndDate = "",
//                        location = "",
//                        status = application.status,
//                        startEventDate = "",
//                        organizerId = 0
//                    )
//                }
//            } else {
//                // Jika pengguna adalah Penyelenggara Bazar, ambil riwayat bazar
//                apiService.getBazarHistory()
//            }
//            isLoading = false
//        } catch (e: Exception) {
//            isLoading = false
//            errorMessage = "Failed to fetch data: ${e.message}"
//        }
//    }

    LaunchedEffect(userRole) {
        viewModel.fetchData(userRole)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (userRole == "umkm") "My Applied Bazaars" else "My Created Bazaars",
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) { padding ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF6C63FF))
                }
            }
            errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = errorMessage!!, color = Color.Red)
                        Button(
                            onClick = { /* Retry logic */ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6C63FF))
                        ) {
                            Text("Retry", color = Color.White)
                        }
                    }
                }
            }
            bazaars.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (userRole == "umkm") "You haven't applied to any bazaars yet."
                        else "You haven't created any bazaars yet.",
                        color = Color.Gray
                    )
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(bazaars) { bazaar ->
                        EventCard(
                            bazaar = bazaar,
                            userRole = userRole,
                            onProgressClick = { /* Handle progress click */ }
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun EventCard(
    bazaar: BazarResponse,
    userRole: String,
    onProgressClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.bazaar_image),
                contentDescription = "Event Logo",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = bazaar.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = bazaar.location,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "Status: ${bazaar.status}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Button(
                onClick = onProgressClick,
                modifier = Modifier.height(36.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6C63FF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = if (userRole == "Penyelenggara Bazar") "Manage" else "See Progress",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Composable
private fun Chip(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        elevation = 0.dp,
        shape = RoundedCornerShape(4.dp),
        color = backgroundColor
    ) {
        Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            content()
        }
    }
}