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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bazarkuy.ui.theme.Poppins

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeScreen(
                onContinueClicked = {
                    // Navigasi ke halaman login
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish() // Menutup WelcomeActivity
                }
            )
        }
    }
}

@Composable
fun WelcomeScreen(onContinueClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5D72E9))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gambar logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Welcome!",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White
                ),
                modifier = Modifier.padding(top = 5.dp)
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

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = onContinueClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF65D696)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Let's Continue",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.White // Warna teks biru
                    )
                )
            }
        }
    }
//    ---
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF5D72E9)) // Background biru
//            .padding(16.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center, // Menggunakan Center agar elemen berada di tengah layar
//            modifier = Modifier.fillMaxSize() // Agar Column mengisi seluruh layar
//        ) {
//            // Bagian logo di atas
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "Logo",
//                modifier = Modifier.size(150.dp) // Ukuran gambar
//            )
//
//            // Teks Welcome
//            Text(
//                text = "Welcome!",
//                style = TextStyle(
//                    fontFamily = Poppins,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 32.sp,
//                    color = Color.White
//                ),
//                modifier = Modifier.padding(top = 16.dp)
//            )
//
//            // Teks Grow Your Business
//            Text(
//                text = "Grow Your Business,\nReach More Customers!",
//                style = TextStyle(
//                    fontFamily = Poppins,
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 18.sp,
//                    color = Color.White
//                ),
//                modifier = Modifier.padding(top = 8.dp),
//                textAlign = androidx.compose.ui.text.style.TextAlign.Center
//            )

            // Spacer untuk memisahkan teks dan tombol
//            Spacer(modifier = Modifier.weight(1f)) // Membuat ruang di antara teks dan tombol

            // Tombol Let's Continue

        }
