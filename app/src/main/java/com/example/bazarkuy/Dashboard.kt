package com.example.bazarkuy

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bazarkuy.R

class Dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DashboardScreen()
                }
        }
    }
}

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(text = "Hello, User!", style = TextStyle(fontSize = 24.sp, color = Color.Black))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Visit our market day and find the best deals!",
            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Slider for Ongoing Bazaar
        Text(text = "Ongoing Bazaar", style = TextStyle(fontSize = 20.sp, color = Color.Black))
        Spacer(modifier = Modifier.height(8.dp))
        OngoingBazaarSlider()
        Spacer(modifier = Modifier.height(16.dp))

        // Coming Soon Bazaar
        Text(text = "Coming Soon Bazaar", style = TextStyle(fontSize = 20.sp, color = Color.Black))
        Spacer(modifier = Modifier.height(8.dp))
        ComingSoonBazaarList()
        Spacer(modifier = Modifier.height(16.dp))

        // Apply Now
        Text(text = "Apply Now", style = TextStyle(fontSize = 20.sp, color = Color.Black))
        Spacer(modifier = Modifier.height(8.dp))
        ApplyNowSection()
    }
}

@Composable
fun OngoingBazaarSlider() {
    LazyRow {
        items(5) { // Example for 5 items
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .size(250.dp, 150.dp)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.bazaar_image), // Replace with your image resource
                        contentDescription = "Bazaar Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .background(Color.Black.copy(alpha = 0.6f))
                            .padding(8.dp)
                    ) {
                        Text(text = "Bazaar Name", color = Color.White, fontSize = 16.sp)
                        Text(text = "Ends: 2024-12-31", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ComingSoonBazaarList() {
    Column {
        repeat(3) { // Example for 3 items
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bazaar_image), // Replace with your image resource
                    contentDescription = "Bazaar Coming Soon",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Bazaar Name", fontSize = 16.sp, color = Color.Black)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calender), // Replace with calendar icon
                            contentDescription = "Calendar Icon",
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "2025-01-01", fontSize = 12.sp, color = Color.Gray)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location), // Replace with location icon
                            contentDescription = "Location Icon",
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Bazaar Location", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun ApplyNowSection() {
    Button(
        onClick = { /* Add action */ },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
    ) {
        Text(text = "Apply for Bazaar", color = Color.White, fontSize = 16.sp)
    }
}
