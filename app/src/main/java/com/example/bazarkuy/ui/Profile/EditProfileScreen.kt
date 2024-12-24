//EditprofileScreen.kt
package com.example.bazarkuy.ui.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.R
import com.example.bazarkuy.ui.theme.Poppins // Ensure this is the correct import for your font

@Composable
fun EditProfileScreen(navController: NavController) {
    var nama by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var facebook by remember { mutableStateOf("") }
    var tiktok by remember { mutableStateOf("") }
    var instagram by remember { mutableStateOf("") }
    val bazaars = listOf("Bazaar 1", "Bazaar 2", "Bazaar 3", "Bazaar 4", "Bazaar 5")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BackButton(navController)
        ProfilePicture()
        ProfilePictureButton()
        Spacer(modifier = Modifier.height(40.dp))

        // Form Fields
        FormTextField("Nama", nama) { nama = it }
        FormTextField("Email", username) { username = it }
        FormTextField("Password", username) { username = it }
        FormTextField("New Password", username) { username = it }
        FormTextField("Confirm Password", username) { username = it }
        FormTextField("Kategori", username) { username = it }

        // Social Media Inputs
        SocialMediaInput("Facebook", facebook) { facebook = it }
        SocialMediaInput("Instagram", instagram) { instagram = it }
        SocialMediaInput("TikTok", tiktok) { tiktok = it }

        Spacer(modifier = Modifier.height(16.dp))

        // Gallery Section
        GallerySection()
    }
}

@Composable
fun BackButton(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ProfilePicture() {
    Box(
        modifier = Modifier.fillMaxSize() // or a specific size
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile_placeholder),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .align(Alignment.Center) // Correct usage in a Box
        )
    }
}

@Composable
fun ProfilePictureButton() {
    Box(
        modifier = Modifier.fillMaxWidth() // or a specific size
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Ubah Foto Profil */ },
            modifier = Modifier
                .align(Alignment.Center) // Correct usage in a Box
                .padding(8.dp), // Optional padding
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3366FF)),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("Ubah Foto Profile", fontFamily = Poppins, color = Color.White)
        }
    }
}

@Composable
fun FormTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Text(label, fontSize = 14.sp)
    Spacer(modifier = Modifier.height(4.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("") },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(10.dp),
    )
    Spacer(modifier = Modifier.height(14.dp))
}

@Composable
fun SocialMediaInput(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = when (label) {
                "Facebook" -> R.drawable.ic_facebook
                "Instagram" -> R.drawable.ic_instagram
                "TikTok" -> R.drawable.ic_tiktok
                else -> R.drawable.ic_profile_placeholder // Fallback
            }),
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("") },
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            shape = RoundedCornerShape(10.dp),
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun GallerySection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Gallery",
            fontFamily = Poppins,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        // Upload Product Photo Button
        IconButton(
            onClick = {
                // Logic to upload product photo
                // For example, adding an image to the gallery
                // galleryImages = galleryImages + R.drawable.new_product_image
            },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Upload Product Photo",
                tint = Color(0xFF3366FF)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    // Divider before the gallery
    Divider(color = Color.Gray, thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    val navController = rememberNavController()
    EditProfileScreen(navController)
}
