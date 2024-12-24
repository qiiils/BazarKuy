package com.example.bazarkuy.ui.dashboard

import DashboardViewModel
import DashboardViewModelFactory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import com.example.bazarkuy.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.example.bazarkuy.data.remote.response.BazarResponse
import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.ui.Navigation.CustomBottomNavigation
import com.example.bazarkuy.ui.Navigation.SetupNavGraph
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.bazarkuy.ui.Navigation.Screen
import androidx.compose.foundation.clickable
import com.example.bazarkuy.ui.BazarDetail.BazarDetail
import com.example.bazarkuy.ui.BazarDetail.BazarDetailScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.FabPosition

// Dashboard.kt
class Dashboard : ComponentActivity() {
    private val viewModel by viewModels<DashboardViewModel> { DashboardViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRole = intent.getStringExtra("USER_ROLE")

        Log.d("Dashboard", "User Role: $userRole")

        setContent {
            val navController = rememberNavController()
            Surface(modifier = Modifier.fillMaxSize()) {
                var showDebugRole by remember { mutableStateOf(false) }

                Scaffold(
                    bottomBar = {
                        CustomBottomNavigation(navController = navController)
                    },
                    floatingActionButton = {
                        Log.d("Dashboard", "Checking FAB condition. Is Penyelenggara? ${userRole == "Penyelenggara Bazar"}")

                        if (userRole == "Penyelenggara Bazar") {
                            FloatingActionButton(
                                onClick = {
                                    // Navigate to AddBazarForm
//                                    startActivity(Intent(this@Dashboard, AddBazarForm::class.java))
                                },
                                containerColor = Color(0xFF4B6BFF),
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Bazar",
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        if (showDebugRole) {
                            Text(
                                text = "Current Role: $userRole",
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(8.dp)
                            )
                        }

                        SetupNavGraph(
                            navController = navController,
                            dashboardViewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}
//    private fun navigateToBazarDetail(role: String) {
//        intent.putExtra("USER_ROLE", role) // Kirim role ke Dashboard
//        startActivity(Intent(this, BazarDetail::class.java))
//        finish() // Hentikan LoginActivity
//    }

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onBazarClick: (Int) -> Unit
) { // tambahkan parameter onBazarClick
        LaunchedEffect(Unit) {
            viewModel.fetchBazaars()
        }

        val ongoingBazaars = viewModel.ongoingBazaars.value
        val comingSoonBazaars = viewModel.comingSoonBazaars.value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.White)
                    )
                )
                .padding(16.dp)
        ) {
            // Header
            Text(
                text = "Hello, User!",
                style = TextStyle(fontSize = 24.sp, color = Color.Black, letterSpacing = 1.5.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Visit our market day and find the best deals!",
                style = TextStyle(fontSize = 16.sp, color = Color.Gray)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Slider for Ongoing Bazaar
            Text(
                text = "Ongoing Bazaar",
                style = TextStyle(fontSize = 20.sp, color = Color.Black, letterSpacing = 1.2.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OngoingBazaarSlider(
                bazaars = ongoingBazaars,
                onBazarClick = onBazarClick  // tambahkan ini
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Coming Soon Bazaar
            Text(
                text = "Coming Soon Bazaar",
                style = TextStyle(fontSize = 20.sp, color = Color.Black, letterSpacing = 1.2.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ComingSoonBazaarList(
                bazaars = comingSoonBazaars,
                onBazarClick = onBazarClick  // tambahkan ini
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

@Composable
fun OngoingBazaarSlider(bazaars: List<BazarResponse>, onBazarClick: (Int) -> Unit) {
    println("Rendering OngoingBazaarSlider with ${bazaars.size} bazaars") // Tambahkan logging

        if (bazaars.isEmpty()) {
            Text(text = "No ongoing bazaars available", color = Color.Gray)
        } else {
            LazyRow {
                items(bazaars.size) { index ->
                    val bazar = bazaars[index]
                    println("Rendering bazar: $bazar") // Tambahkan logging
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = 8.dp,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(250.dp, 150.dp)
                            .clickable { onBazarClick(bazar.id) }
                    ) {
                        Box {

                            Image(
                                painter = painterResource(id = R.drawable.bazaar_image),
                                contentDescription = "Bazaar Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .background(Color.Black.copy(alpha = 0.6f))
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = bazar.name,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = "Periode: ${bazar.startEventDate} - ${bazar.endEventDate}",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
//                            Text(
//                                text = "Ends: ${bazar.endEventDate}",
//                                color = Color.White,
//                                fontSize = 12.sp
//                            )
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun ComingSoonBazaarList(bazaars: List<BazarResponse>, onBazarClick: (Int) -> Unit) {
    println("Rendering ComingSoonBazaarList with ${bazaars.size} bazaars") // Tambahkan logging

        if (bazaars.isEmpty()) {
            Text(text = "No upcoming bazaars available", color = Color.Gray)
        } else {
            Column {
                bazaars.forEach { bazar ->
                    println("Rendering coming soon bazar: $bazar") // Tambahkan logging
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { onBazarClick(bazar.id) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bazaar_image),
                            contentDescription = "Bazaar Coming Soon",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = bazar.name,
                                fontSize = 16.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Date: ${bazar.registrationStartDate}",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = "Location: ${bazar.location}",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
