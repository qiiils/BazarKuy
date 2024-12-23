//ProfileScreen.kt
package com.example.bazarkuy

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.bazarkuy.ui.theme.Poppins

@Composable
fun ProfileScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val bazaars = listOf("Bazaar 1", "Bazaar 2", "Bazaar 3", "Bazaar 4", "Bazaar 5")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BackButton()
        ProfilePicture()
        EditProfileButton(navController)
        UserInfoField("Nama", name)
        UserInfoField("Email", email)
        UserInfoField("Password", password.replace(Regex("."), "*")) // Mask password
        UserInfoField("Kategori", category)
        SocialMediaLinks()
        GallerySection()
        BazaarsList(bazaars)
    }
}

@Composable
fun BackButton() {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            onClick = { /* Aksi Kembali */ },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back), // Ganti dengan ikon kembali yang sesuai
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
        modifier = Modifier
            .fillMaxWidth() // Fill the width of the parent
            .wrapContentHeight(), // Wrap the height to the content
        contentAlignment = Alignment.Center // Center the content
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile_placeholder), // Placeholder
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun EditProfileButton(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { navController.navigate("editProfile") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3366FF))
        ) {
            Text("Edit Profile", fontFamily = Poppins, color = Color.White)
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun UserInfoField(label: String, value: String) {
    Text(label, fontFamily = Poppins, fontSize = 14.sp)
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = value, // Display the value
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(10.dp)
            ) // Adding border
            .padding(16.dp), // Padding for better appearance
        fontFamily = Poppins,
        fontSize = 14.sp
    )
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun SocialMediaLinks() {
    Box(
        modifier = Modifier.fillMaxWidth(), // Fill the width of the parent
        contentAlignment = Alignment.Center // Center the content
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically // Center vertically if needed
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "Facebook",
                modifier = Modifier.size(32.dp),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_instagram),
                contentDescription = "Instagram",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_tiktok),
                contentDescription = "Tiktok",
                modifier = Modifier.size(32.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun GallerySection() {
    // Title and Upload Button
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
        // Upload Photo Button
        IconButton(
            onClick = {
                // Logic to upload product photo
                // For example, adding an image to the gallery
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
    // Divider before Gallery
    Divider(color = Color.Gray, thickness = 1.dp)
}

@Composable
fun BazaarsList(bazaars: List<String>) {
    // Title for Bazaars
    Text(
        text = "List Bazaars",
        fontFamily = Poppins,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    // Horizontal Scrollable List of Bazaars
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(bazaars) { bazaar ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = bazaar,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* Navigate to Bazaar Details */ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3366FF))
                    ) {
                        Text("View", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}