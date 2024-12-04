package com.example.bazarkuy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeScreen(
                onContinueClicked = {
                    // Navigasi ke halaman login
                    navigateToLogin()
                }
            )
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish() // Menutup WelcomeActivity
    }
}

@Composable
fun WelcomeScreen(onContinueClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5D72E9)) // Background biru
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            // Bagian logo di atas
            Image(
                painter = painterResource(id = R.drawable.logo), // Sesuaikan dengan nama file logo
                contentDescription = "Logo",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Teks Welcome
            Text(
                text = "Welcome!",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            // Teks Grow Your Business
            Text(
                text = "Grow Your Business,\nReach More Customers!",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = Color.White
                ),
                modifier = Modifier.padding(top = 8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // Spacer untuk memisahkan teks dan tombol
            Spacer(modifier = Modifier.weight(1f))

            // Tombol Let's Continue
            Button(
                onClick = onContinueClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Let's Continue",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFF5D72E9) // Warna teks biru
                    )
                )
            }
        }
    }
}