package com.example.bazarkuy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                onLoginClick = { userType -> navigateToDashboard(userType) },
                onForgotPasswordClick = { navigateToForgotPassword() },
                onSignUpClick = { navigateToRegister() }
            )
        }
    }

    private fun navigateToDashboard(userType: String) {
        val intent = Intent(this, Dashboard::class.java)
        intent.putExtra("USER_TYPE", userType) // Passing user type to Dashboard
        startActivity(intent)
    }

    private fun navigateToForgotPassword() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun LoginScreen(
    onLoginClick: (String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                color = Color.Black
            )
            )
        Text(
            text = "Back!",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

    CustomTextField(
        value = username.value.text,
        onValueChange = { username.value = username.value.copy(text = it) },
        label = "Username",
        iconRes = R.drawable.ic_username,
        placeholder = "Enter your username"
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = password.value.text,
        onValueChange = { password.value = password.value.copy(text = it) },
        label = "Password",
        isPassword = true,
        iconRes = R.drawable.ic_password,
        placeholder = "Enter your password"
    )

        Spacer(modifier = Modifier.height(1.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        TextButton(onClick = { onForgotPasswordClick() }) {
            Text(
                text = "Forgot Password?",
                style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Red
                )
            )
        }
    }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp), // Spasi antar tombol
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onLoginClick("UMKM") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5D72E9)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = "Login as UMKM/Pengguna")
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = { onLoginClick("Penyelenggara") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5D72E9)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = "Login as Penyelenggara")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box (
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TextButton(onClick = onSignUpClick) {
                Text(
                    text = "Create an Account",
                    color = Color(0xFF5D72E9),
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    iconRes: Int,
    placeholder: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
            .height(45.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color(0xFF5D72E9),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        ) { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                )
            }
            innerTextField()
        }
    }
}
