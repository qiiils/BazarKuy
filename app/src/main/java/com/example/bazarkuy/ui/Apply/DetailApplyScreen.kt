package com.example.bazarkuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.example.bazarkuy.ui.theme.Poppins

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessProfileScreen()
        }
    }
}

@Composable
fun BusinessProfileScreen() {
    Scaffold(
        topBar = {
            // Custom Header without TopAppBar
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White) // Set header background to white
                    .padding(16.dp) // Add padding to the header
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { /* TODO: Handle back button click */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back), // Replace with your back icon resource
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp), // Adjust size as needed
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Space between icon and title
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            ProfileSection()
            Spacer(modifier = Modifier.height(16.dp))
            BusinessDetails()
        }
    }
}

@Composable
fun ProfileSection() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.bazaar_image), // Replace with your image
            contentDescription = "Business profile image",
            modifier = Modifier
                .size(height = 150.dp,width =100.dp )
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Mie Gacoan",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )
            Button(
                onClick = { /* TODO: Handle view profile click */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5D72E9)), // Updated color
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "Lihat Profil",
                    color = Color.White,
                    fontFamily = Poppins,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            ActionButtons()
        }
    }
}

@Composable
fun ActionButtons() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /* TODO: Handle accept click */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF65D696)), // Updated color
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .height(40.dp) // Reduced height
        ) {
            Text(
                text = "Terima",
                color = Color.White,
                fontFamily = Poppins,
                fontSize = 14.sp // Reduced font size
            )
        }
        Button(
            onClick = { /* TODO: Handle decline click */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .height(40.dp) // Reduced height
        ) {
            Text(
                text = "Tolak",
                color = Color.Black,
                fontFamily = Poppins,
                fontSize = 14.sp // Reduced font size
            )
        }
    }
}

@Composable
fun BusinessDetails() {
    Column {
        DetailSection(
            title = "Produk dan Layanan",
            content = "Kami menyediakan berbagai varian Mie Gacoan seperti Mie Setan dan Mie Iblis, serta menu pelengkap seperti dimsum, pangsit goreng, dan minuman segar."
        )
        DetailSection(
            title = "Keterangan Tambahan",
            content = "Butuh informasi lebih lanjut mengenai area distribusi."
        )
    }
}

@Composable
fun DetailSection(title: String, content: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .background(Color.White, shape = RoundedCornerShape(5.dp))
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
        ) {
            Text(
                text = content,
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = Poppins
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessProfileScreenPreview() {
    BusinessProfileScreen()
}