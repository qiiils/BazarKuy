import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.R
import com.example.bazarkuy.ui.theme.Poppins

@Composable
fun ProgressScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                        )
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.bazaar_image),
                    contentDescription = "ISCE Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        "ISCE UNAND",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = Poppins
                    )
                    Text(
                        "by Sistem Informasi",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = Poppins
                    )
                    Text(
                        "Fakultas Teknologi Informasi",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = Poppins

                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),  // Reduced spacing between circles
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgressCircle(number = 1, text = "Kamu sudah\nberhasil apply!", isActive = true)
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.LightGray,
                    thickness = 2.dp
                )
                ProgressCircle(number = 2, text = "Status :\nterdaftar!", isActive = false)
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = 4.dp,
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "BEM Logo",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        "BEM KM FTI UNAND",
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = Poppins
                    )
                    Text(
                        "Penyelenggara",
                        modifier = Modifier
                            .background(
                                color = Color(0xFF4CAF50),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = Poppins
                    )
                }
            }
        }
    }
}

//@Composable
//fun ProgressCircles(acceptanceStatus: Boolean) {
//    Row(
//        horizontalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        ProgressCircle(
//            number = 1,
//            text = "First Step",
//            isActive = true
//        )
//
//        Spacer(modifier = Modifier.width(32.dp))
//
//        ProgressCircle(
//            number = 2,
//            text = "Second Step",
//            isActive = acceptanceStatus
//        )
//    }
//}

@Composable
private fun ProgressCircle(
    number: Int,
    text: String,
    isActive: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)  // Increased from 80.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)  // Increased from 40.dp
                .background(
                    color = if (isActive) Color(0xFF6C63FF) else Color.LightGray,
                    shape = RoundedCornerShape(25.dp)  // Adjusted for larger size
                )
                .border(  // Added green border
                    width = 2.dp,
                    color = Color(0xFF4CAF50),
                    shape = RoundedCornerShape(25.dp)
                )
        ) {
            Text(
                text = number.toString(),
                color = if (isActive) Color.White else Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = Poppins// Added larger font size
            )
        }
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = Poppins,// Increased from 12.sp
            color = if (isActive) Color.Black else Color.Gray,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressScreen() {
    // Buat NavController dummy untuk preview
    val navController = rememberNavController()
    ProgressScreen(navController = navController)
}