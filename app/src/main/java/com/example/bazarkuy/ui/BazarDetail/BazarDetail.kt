package com.example.bazarkuy.ui.BazarDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazarkuy.R
import com.example.bazarkuy.data.remote.response.BazarDetailResponse
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.data.remote.retrofit.ApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bazarkuy.data.local.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class BazarDetail : ComponentActivity() {
    private lateinit var token: String
    private val viewModel by viewModels<BazarDetailViewModel> { BazarDetailViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            token = runBlocking {
                UserPreferences().getToken(this@BazarDetail) ?: ""
            }

            val bazarId = intent.getIntExtra("bazarId", -1)
            if (bazarId == -1) {
                finish()
                return
            }

            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BazarDetailScreen(
                        bazarId = bazarId,
                        onBackClick = { finish() }
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            finish()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BazarDetailScreen(
    bazarId: Int,
    onBackClick: () -> Unit,
    viewModel: BazarDetailViewModel = viewModel(factory = BazarDetailViewModelFactory(LocalContext.current))
) {
    val viewModel: BazarDetailViewModel = viewModel()
    val bazarDetail by viewModel.bazarDetail
    val isLoading by viewModel.isLoading
    val error by viewModel.error
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(bazarId) {
        scope.launch {
            viewModel.fetchBazarDetail(bazarId)
        }
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Detail Bazar") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                error != null -> {
                    Text(
                        text = error ?: "Unknown error occurred",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                bazarDetail != null -> {
                    val bazar = bazarDetail!!

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        // Banner Image
                        Image(
                            painter = painterResource(id = R.drawable.bazaar_image),
                            contentDescription = "Bazar Banner",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Title
                        Text(
                            text = bazar.name,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Description
                        Text(
                            text = bazar.description,
                            style = TextStyle(fontSize = 16.sp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Details
                        Text(
                            text = "Detail",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        DetailItem("Lokasi", bazar.location)
                        DetailItem("Tanggal Event", "${bazar.startEventDate} - ${bazar.endEventDate}")
                        DetailItem("Periode Registrasi", "${bazar.registrationStartDate} - ${bazar.registrationEndDate}")
                        DetailItem("Status", bazar.status)

                        Spacer(modifier = Modifier.height(24.dp))

                        // Apply Button
                        Button(
                            onClick = { /* TODO: Handle registration */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Daftar Sekarang")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label:",
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            modifier = Modifier.weight(2f)
        )
    }
}

@Composable
fun UMKMCard(
    name: String,
    category: String,
    imageRes: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = category,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "View Detail",
                tint = Color.Gray
            )
        }
    }
}
@Preview(
    name = "Bazar Detail Screen",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4
)
@Composable
fun BazarDetailScreenPreview() {
    BazarDetailScreen(
        bazarId = 1,
        onBackClick = {}
    )
}