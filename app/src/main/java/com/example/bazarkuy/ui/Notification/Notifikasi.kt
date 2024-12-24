package com.example.bazarkuy.ui.Notification


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.R

@Composable
fun NotificationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle back button click */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back), // Replace with your back icon resource
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp), // Adjust size as needed
                        )
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            NotificationCard(
                title = "PENDAFTARAN DITOLAK",
                message = "Terima kasih telah mendaftar untuk berpartisipasi di Bazar ISCE Unand. Namun, dengan sangat menyesal kami informasikan bahwa pendaftaran UMKM Anda, Mie Gacoan, telah ditolak",
                icon = Icons.Filled.Clear,
                iconTint = Color.Red
            )

            NotificationCard(
                title = "ANDA TELAH MENDAFTAR",
                message = "Selamat! Pendaftaran Anda untuk Bazar ISCE Unand telah terkirim. Tunggu info selanjutnya dari Penyelenggara!",
                icon = Icons.Filled.CheckCircle,
                iconTint = Color(0xFF4CAF50)
            )
        }
    }
}

@Composable
fun NotificationCard(title: String, message: String, icon: ImageVector, iconTint: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, Color(0xFF5D72E9), RoundedCornerShape(16.dp)), // Added border
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    color = iconTint,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = message,
                    color = Color(0xFF5D72E9),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationScreenPreview() {
    val navController = rememberNavController()
    NotificationScreen(navController = navController)
}