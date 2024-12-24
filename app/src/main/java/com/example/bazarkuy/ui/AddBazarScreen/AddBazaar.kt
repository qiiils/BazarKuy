package com.example.bazarkuy.ui.AddBazarScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.R

@Composable
fun DetailApply(navController: NavController) {
    // Previous implementation remains the same
    var deskripsiAcara by remember { mutableStateOf("") }
    var namaAcara by remember { mutableStateOf("") }
    var tanggalPelaksanaan by remember { mutableStateOf("") }
    var lokasi by remember { mutableStateOf("") }
    var tema by remember { mutableStateOf("") }

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
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
        ) {
            Text(
                "Deskripsi Acara",
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Gray
            )
            OutlinedTextField(
                value = deskripsiAcara,
                onValueChange = { deskripsiAcara = it },
                placeholder = { Text("Type something...", color = Color.LightGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp),
                shape = RoundedCornerShape(8.dp)
            )

            Text(
                "Nama Acara",
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                color = Color.Gray
            )
            OutlinedTextField(
                value = namaAcara,
                onValueChange = { namaAcara = it },
                placeholder = { Text("Type something...", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Text(
                "Tanggal Pelaksanaan",
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                color = Color.Gray
            )
            OutlinedTextField(
                value = tanggalPelaksanaan,
                onValueChange = { tanggalPelaksanaan = it },
                placeholder = { Text("Type something...", color = Color.LightGray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Text(
                "Lokasi",
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                color = Color.Gray
            )
            OutlinedTextField(
                value = lokasi,
                onValueChange = { lokasi = it },
                placeholder = { Text("Type something...", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Text(
                "Tema",
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                color = Color.Gray
            )
            OutlinedTextField(
                value = tema,
                onValueChange = { tema = it },
                placeholder = { Text("Type something...", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Text(
                "Upload Icon (1000 × 1000)",
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

            Text(
                "Upload Header (2000 × 1000)",
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

            Button(
                onClick = { },
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailApplyPreview() {
    val navController = rememberNavController()
    DetailApply(navController = navController)
}