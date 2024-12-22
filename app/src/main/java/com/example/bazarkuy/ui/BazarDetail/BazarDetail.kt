package com.example.bazarkuy.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bazarkuy.R
import com.example.bazarkuy.ui.bazardetail.BazarDetailViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BazarDetailScreen(
    bazarId: Int,
    onBackClick: () -> Unit,
    viewModel: BazarDetailViewModel = viewModel()
) {
    val bazarDetail by viewModel.bazarDetail
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    LaunchedEffect(bazarId) {
        viewModel.fetchBazarDetail(bazarId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Bazar") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (error != null) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = error ?: "Terjadi kesalahan",
                        color = Color.Red
                    )
                }
            } else {
                bazarDetail?.let { bazar ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Banner Image
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bazaar_image),
                                contentDescription = "Bazar Banner",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        // Main Content
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // Title Section
                            Text(
                                text = bazar.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            // Status Chip
                            Surface(
                                color = when (bazar.status.lowercase()) {
                                    "ongoing" -> Color(0xFF4CAF50)
                                    "draft" -> Color(0xFF2196F3)
                                    "completed" -> Color(0xFF9E9E9E)
                                    "cancelled" -> Color(0xFFE57373)
                                    else -> Color.Gray
                                },
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    text = when (bazar.status.lowercase()) {
                                        "ongoing" -> "Sedang Berlangsung"
                                        "draft" -> "Akan Datang"
                                        "completed" -> "Selesai"
                                        "cancelled" -> "Dibatalkan"
                                        else -> "Status Tidak Diketahui"
                                    },
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(16.dp))

                            // Info Section
                            DetailSection(
                                title = "Informasi Bazar",
                                items = listOf(
                                    DetailItem(
                                        icon = Icons.Default.DateRange,
                                        label = "Tanggal Acara",
                                        value = formatDate(bazar.eventDate)
                                    ),
                                    DetailItem(
                                        icon = Icons.Default.LocationOn,
                                        label = "Lokasi",
                                        value = bazar.location
                                    ),
                                    DetailItem(
                                        icon = Icons.Default.Person,
                                        label = "Penyelenggara",
                                        value = bazar.organizer.name
                                    )
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Registration Period
                            DetailSection(
                                title = "Periode Pendaftaran",
                                items = listOf(
                                    DetailItem(
                                        icon = Icons.Default.DateRange,
                                        label = "Mulai Pendaftaran",
                                        value = formatDate(bazar.registrationStartDate)
                                    ),
                                    DetailItem(
                                        icon = Icons.Default.DateRange,
                                        label = "Akhir Pendaftaran",
                                        value = formatDate(bazar.registrationEndDate)
                                    )
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Terms and Conditions
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = 4.dp,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "Syarat dan Ketentuan",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = bazar.termsAndConditions,
                                        color = Color.Gray
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            // Apply Button
                            Button(
                                onClick = { /* Handle registration */ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.primary
                                )
                            ) {
                                Text(
                                    "Daftar Sekarang",
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailSection(
    title: String,
    items: List<DetailItem>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = item.label,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = item.value,
                            fontSize = 16.sp
                        )
                    }
                }
                if (items.last() != item) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

data class DetailItem(
    val icon: ImageVector,
    val label: String,
    val value: String
)

private fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id"))
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(dateString)
        outputFormat.format(date)
    } catch (e: Exception) {
        dateString
    }
}