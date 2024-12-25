// ListProgressScreen.kt
package com.example.bazarkuy.ui.Tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bazarkuy.R

@Composable
fun ListProgressScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Where you applied to", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Current Application Card
            EventCard(
                title = "ISCE UNAND",
                date = "25-28 Desember 2024",
                location = "PKM Unand",
                isNew = true
            )

            // Section Title
            Text(
                "Last time you applied",
                modifier = Modifier.padding(vertical = 16.dp),
                fontWeight = FontWeight.Medium
            )

            // Previous Application Card
            EventCard(
                title = "LKMM-TM SI",
                date = "25-28 Desember 2024",
                location = "PKM Unand",
                isNew = false
            )
        }
    }
}

@Composable
private fun EventCard(
    title: String,
    date: String,
    location: String,
    isNew: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.bazaar_image),
                contentDescription = "Event Logo",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    if (isNew) {
                        Text(
                            text = "!",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = date,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = location,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            Button(
                onClick = { /* Handle click */ },
                modifier = Modifier.height(36.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6C63FF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "See Progress",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}