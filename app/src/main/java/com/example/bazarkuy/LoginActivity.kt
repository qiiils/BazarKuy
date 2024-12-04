package com.example.bazarkuy

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                onLoginClick = { /* TODO: Implement login logic */ },
                onForgotPasswordClick = { navigateToForgotPassword() },
                onSignUpClick = { navigateToRegister() }
            )
        }
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
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    // State for input fields
    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Welcome Text
        Text(
            text = "Welcome!",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Username Input
        CustomTextField(
            value = username,
            label = "Username",
            isPassword = false,
            iconRes = R.drawable.ic_username // Ensure this drawable exists
        )

        // Password Input
        CustomTextField(
            value = password,
            label = "Password",
            isPassword = true,
            iconRes = R.drawable.ic_password // Ensure this drawable exists
        )

        // Forgot Password Button
        val context = LocalContext.current
        TextButton(onClick = { onForgotPasswordClick() }) {
            Text(text = "Forgot Password?", color = Color.Blue)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onLoginClick, modifier = Modifier.weight(1f)) {
                Text(text = "Login as UMKM/Pengguna")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onLoginClick, modifier = Modifier.weight(1f)) {
                Text(text = "Login as Penyelenggara")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Navigation
        TextButton(onClick = onSignUpClick) {
            Text(text = "Create an Account", color = Color.Blue)
        }
    }
}

@Composable
fun CustomTextField(
    value: androidx.compose.runtime.MutableState<TextFieldValue>,
    label: String,
    isPassword: Boolean,
    iconRes: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .background(Color.Gray.copy(alpha = 0.1f))
                .border(1.dp, Color.Gray)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Icon",
                modifier = Modifier.padding(end = 16.dp)
            )
            BasicTextField(
                value = value.value,
                onValueChange = { value.value = it },
                textStyle = TextStyle(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Label
        Text(
            text = label,
            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
