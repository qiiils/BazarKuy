package com.example.bazarkuy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SubmitFormScreen(navController: NavController) {
    var namaUsaha by remember { mutableStateOf("") }
    var deskripsiUsaha by remember { mutableStateOf("") }
    var produkLayanan by remember { mutableStateOf("") }
    var keteranganTambahan by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val placeholderText = "Type Something...."
    val buttonColor = Color(0xFF6C63FF)

    Scaffold(
        topBar = {
            Header(onBackClick = { navController.popBackStack() }) // Handle back action
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Input Fields
            InputField(
                value = namaUsaha,
                onValueChange = { namaUsaha = it },
                label = "Nama Usaha",
                placeholder = placeholderText,
                height = 56.dp
            )
            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                value = deskripsiUsaha,
                onValueChange = { deskripsiUsaha = it },
                label = "Deskripsi Usaha",
                placeholder = placeholderText,
                height = 150.dp
            )
            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                value = produkLayanan,
                onValueChange = { produkLayanan = it },
                label = "Produk dan Layanan",
                placeholder = placeholderText,
                height = 150.dp
            )
            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                value = keteranganTambahan,
                onValueChange = { keteranganTambahan = it },
                label = "Keterangan Tambahan",
                placeholder = placeholderText,
                height = 150.dp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            SubmitButton(
                onClick = {
                    // Handle apply action, e.g., validate fields and submit
                    if (namaUsaha.isNotBlank() && deskripsiUsaha.isNotBlank() &&
                        produkLayanan.isNotBlank() && keteranganTambahan.isNotBlank()) {
                        // Submit the form
                    } else {
                        showError = true
                    }
                },
                buttonColor = buttonColor
            )

            // Error Message
            if (showError) {
                Text(
                    text = "Please fill in all fields.",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun Header(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text("Submit Form") },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        backgroundColor = Color(0xFF6200EE),
        contentColor = Color.White
    )
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    height: Dp
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun SubmitButton(onClick: () -> Unit, buttonColor: Color) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Submit", color = Color.White, fontSize = 16.sp)
    }
}
@Preview(showBackground = true)
@Composable
fun SubmitFormScreenPreview() {
    SubmitFormScreen(navController = rememberNavController())
}