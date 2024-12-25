package com.example.bazarkuy.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.material3.Text
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.ProfileScreen
import com.example.bazarkuy.R
import com.example.bazarkuy.ui.Profile.EditProfileScreen

val Poppins = FontFamily(
    Font(R.font.poppins_semibold)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen(onTimeout = {
                // Menavigasi ke LoginActivity setelah Splash Screen selesai
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish() // Menutup MainActivity setelah pindah ke LoginActivity
            })
        }
    }
}


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "profile"
    ) {
        composable("editProfile") {
            EditProfileScreen(navController)
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Delay untuk splash screen (3 detik)
    LaunchedEffect(Unit) {
        delay(3000)  // Menggunakan delay tanpa DurationUnit karena sudah milidetik
        onTimeout()  // Memanggil fungsi callback setelah delay
    }

    // UI untuk Splash Screen
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color(0xFF5D72E9))
            .paint(
                painter = painterResource(R.drawable.background_launcher),
                contentScale = ContentScale.Crop
            )
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

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "BazarKuy",
                style = TextStyle(
                    fontFamily = com.example.bazarkuy.ui.theme.Poppins,  // Menggunakan font Poppins
                    fontSize = 18.sp,        // Ukuran font
                    color = Color.White      // Warna teks putih
                )
            )
        }
    }
}
