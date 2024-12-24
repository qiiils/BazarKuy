package com.example.bazarkuy.ui.EditBazaar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bazarkuy.ui.theme.Poppins
import androidx.compose.runtime.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import com.example.bazarkuy.R

class EditBazaar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EditBazaarScreen()
        }
    }
}

@Composable
fun EditBazaarScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.5f),
                            Color.Transparent
                        ),
                        startY = 200f,
                        endY = 0f
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.bazaar_image),
                contentDescription = "Bazaar Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "BAZAAR",
                    color = Color.Red,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(onClick = { /* TODO: Handle back button click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = { /* TODO: Handle menu click */ }) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "Menu"
                    )
                }
            }
        }

        TabSection()
    }
}

@Composable
fun TabSection() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Detail Bazaar", "Syarat dan Ketentuan")

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.White,
        contentColor = Color.Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = Color.Black
            )
        }
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index }
            ) {
                Text(
                    text = title,
                    color = if (selectedTabIndex == index) Color.Black else Color.Gray,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    when (selectedTabIndex) {
        0 -> DetailBazaarContent(
            namaBazaar = "",              // Default empty string
            setNamaBazaar = {},            // Default lambda or any update logic
            tanggalPelaksanaan = "",       // Default empty string
            setTanggalPelaksanaan = {},    // Default lambda
            lokasi = "",                   // Default empty string
            setLokasi = {}                 // Default lambda
        )
        1 -> SyaratDanKetentuanContent()
    }
}

@Composable
fun DetailBazaarContent(
    namaBazaar: String,
    setNamaBazaar: (String) -> Unit,
    tanggalPelaksanaan: String,
    setTanggalPelaksanaan: (String) -> Unit,
    lokasi: String,
    setLokasi: (String) -> Unit
)
{
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Detail Bazaar",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Nama Bazaar Field
        Text(
            text = "Deskripsi Acara",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = Poppins
        )
        OutlinedTextField(
            value = namaBazaar,
            onValueChange = setNamaBazaar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))  // Border with rounded corners
                .clip(RoundedCornerShape(10.dp)),  // Clip to ensure clean borders
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, fontFamily = Poppins)
        )

        // Nama Bazaar Field
        Text(
            text = "Nama Bazaar",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = Poppins
        )
        OutlinedTextField(
            value = namaBazaar,
            onValueChange = setNamaBazaar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))  // Border with rounded corners
                .clip(RoundedCornerShape(10.dp)),  // Clip to ensure clean borders
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, fontFamily = Poppins)
        )

        // Nama Bazaar Field
        Text(
            text = "Nama Bazaar",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = Poppins
        )
        OutlinedTextField(
            value = namaBazaar,
            onValueChange = setNamaBazaar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))  // Border with rounded corners
                .clip(RoundedCornerShape(10.dp)),  // Clip to ensure clean borders
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, fontFamily = Poppins)
        )

        // Tanggal Pelaksanaan Field
        Text(
            text = "Tanggal Pelaksanaan",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = Poppins
        )
        OutlinedTextField(
            value = tanggalPelaksanaan,
            onValueChange = setTanggalPelaksanaan,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))  // Border with rounded corners
                .clip(RoundedCornerShape(10.dp)),  // Clip to ensure clean borders
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, fontFamily = Poppins)
        )

        // Lokasi Field
        Text(
            text = "Lokasi",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = Poppins
        )
        OutlinedTextField(
            value = lokasi,
            onValueChange = setLokasi,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))  // Border with rounded corners
                .clip(RoundedCornerShape(10.dp)),  // Clip to ensure clean borders
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, fontFamily = Poppins)
        )
    }
}

@Composable
fun SyaratDanKetentuanContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Syarat dan Ketentuan", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text("1. Kriteria Peserta:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(
                "• Terbuka untuk semua UMKM di bidang kuliner, fashion, kerajinan tangan, teknologi, atau jasa lainnya.\n" +
                        "• UMKM yang memiliki legalitas usaha lebih diutamakan, tetapi usaha rumahan tetap diperbolehkan untuk mendaftar.",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditBazaarPreview() {
    EditBazaarScreen()
}
