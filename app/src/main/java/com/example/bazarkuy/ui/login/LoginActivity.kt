package com.example.bazarkuy.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.bazarkuy.R
import com.example.bazarkuy.ui.dashboard.Dashboard
import com.example.bazarkuy.ui.signUp.RegisterActivity


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
        val intent = Intent(this, com.example.bazarkuy.ui.ForgotPasswordActivity::class.java)
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
    placeholder: String = ""
) {
    val primaryColor = Color(0xFF5D72E9)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = primaryColor,
                modifier = Modifier.size(24.dp)
            )
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            // Warna border saat fokus
            focusedBorderColor = primaryColor,
            // Warna border saat tidak fokus
            unfocusedBorderColor = Color.Gray,

            // Warna label saat fokus
            focusedLabelColor = primaryColor,
            // Warna label saat tidak fokus
            unfocusedLabelColor = Color.Gray,

            // Warna kursor
            cursorColor = primaryColor
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
