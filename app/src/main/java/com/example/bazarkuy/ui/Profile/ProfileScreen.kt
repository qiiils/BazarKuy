//ProfileScreen.kt
package com.example.bazarkuy

import ProfileViewModel
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bazarkuy.data.remote.retrofit.ApiConfig
import com.example.bazarkuy.ui.Profile.ProfileViewModelFactory
import com.example.bazarkuy.ui.theme.Poppins
import com.example.bazarkuy.ui.Profile.BackButton

// ProfileScreen.kt
@Composable
fun ProfileScreen(
    navController: NavController
) {
    // Get the context using LocalContext
    val context = LocalContext.current

    // Initialize ViewModel dengan cara berbeda
    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory.provideFactory(
            context = context,
            apiService = ApiConfig.getApiService(context)
        )
    )

    val profileState by viewModel.profile.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Fetch profile when screen is first displayed
    LaunchedEffect(Unit) {
        viewModel.fetchProfile()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BackButton(navController)
        ProfilePicture()
        EditProfileButton(navController)

        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            error != null -> {
                Text(
                    text = error ?: "",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }
            profileState != null -> {
                profileState?.let { profile ->
                    UserInfoField(label = "Nama", value = profile.name)
                    UserInfoField(label = "Email", value = profile.email)
                    UserInfoField(label = "Username", value = profile.username ?: "")
                    SocialMediaLinks(
                        facebookLink = profile.facebookLink,
                        instagramLink = profile.instagramLink,
                        tiktokLink = profile.tiktokLink
                    )
                }
            }

        else -> {
        Text(
            text = "Profile data not available",
            color = Color.Gray,
            modifier = Modifier.padding(8.dp),
            fontSize = 14.sp
        )
    }
    }
        ChangePasswordSection(viewModel)
        GallerySection()
        BazaarsList(bazaars = emptyList())

    }
}
@Composable
fun BackButton(navController: NavController) {
    IconButton(
        onClick = { navController.navigateUp() }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SocialMediaLinks(
    facebookLink: String?,
    instagramLink: String?,
    tiktokLink: String?
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialMediaIcon(
                iconRes = R.drawable.ic_facebook,
                contentDescription = "Facebook",
                link = facebookLink
            )
            Spacer(modifier = Modifier.width(6.dp))
            SocialMediaIcon(
                iconRes = R.drawable.ic_instagram,
                contentDescription = "Instagram",
                link = instagramLink
            )
            Spacer(modifier = Modifier.width(6.dp))
            SocialMediaIcon(
                iconRes = R.drawable.ic_tiktok,
                contentDescription = "TikTok",
                link = tiktokLink
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
private fun SocialMediaIcon(
    iconRes: Int,
    contentDescription: String,
    link: String?
) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(32.dp)
            .clickable {
                link?.let { /* Handle link click */ }
            }
    )
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
        IconButton(
            onClick = { /* Handle upload */ },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Upload Photo",
                tint = Color(0xFF3366FF)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Divider(color = Color.Gray, thickness = 1.dp)

    // Placeholder jika gallery kosong
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No gallery items yet",
            color = Color.Gray,
            fontFamily = Poppins,
            fontSize = 14.sp
        )
    }
}

@Composable
fun BazaarsList(bazaars: List<String>) {
    Text(
        text = "List Bazaars",
        fontFamily = Poppins,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    if (bazaars.isEmpty()) {
        Text(
            text = "No bazaars available",
            color = Color.Gray,
            fontFamily = Poppins,
            fontSize = 14.sp,
            modifier = Modifier.padding(8.dp)
        )
    } else {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
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
}
@Composable
fun ChangePasswordSection(viewModel: ProfileViewModel) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val isChangingPassword by viewModel.isChangingPassword.collectAsState()
    val changePasswordError by viewModel.changePasswordError.collectAsState()

    // Button untuk membuka dialog
    Button(
        onClick = { showDialog = true },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3366FF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Ubah Password", color = Color.White, fontFamily = Poppins)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Ubah Password",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        label = { Text("Password Saat Ini", fontFamily = Poppins) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )

                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("Password Baru", fontFamily = Poppins) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )

                    if (changePasswordError != null) {
                        Text(
                            text = changePasswordError ?: "",
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp),
                            fontFamily = Poppins,
                            fontSize = 12.sp
                        )
                    }

                    if (isChangingPassword) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 8.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.changePassword(currentPassword, newPassword)
                        if (changePasswordError == null) {
                            currentPassword = ""
                            newPassword = ""
                            showDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3366FF))
                ) {
                    Text("Simpan", color = Color.White, fontFamily = Poppins)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Batal", color = Color(0xFF3366FF), fontFamily = Poppins)
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}