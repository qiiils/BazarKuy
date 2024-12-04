package com.example.bazarkuy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    // Tampilan Login Screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Teks Welcome
        Text(
            text = "Welcome!",
            style = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Username
        CustomTextField(
            value = username,
            label = "Username",
            isPassword = false,
            icon = R.drawable.ic_username // Ganti dengan drawable icon yang sesuai
        )

        // Password
        CustomTextField(
            value = password,
            label = "Password",
            isPassword = true,
            icon = R.drawable.ic_password // Ganti dengan drawable icon yang sesuai
        )

        // Forgot Password
        TextButton(onClick = { onForgotPasswordClick() }) {
            Text(text = "Forgot Password?", color = Color.Blue)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pilihan Login
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Login sebagai UMKM / Pengguna
            Button(onClick = { onLoginClick() }, modifier = Modifier.weight(1f)) {
                Text(text = "Login as UMKM/ Pengguna")
            }

            // Login sebagai Penyelenggara
            Button(onClick = { onLoginClick() }, modifier = Modifier.weight(1f)) {
                Text(text = "Login as Penyelenggara")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pilihan Login dengan Google, Apple, Facebook
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LoginWithButton(icon = R.drawable.ic_google, text = "Google")
            LoginWithButton(icon = R.drawable.ic_apple, text = "Apple")
            LoginWithButton(icon = R.drawable.ic_facebook, text = "Facebook")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign up
        TextButton(onClick = { onSignUpClick() }) {
            Text(text = "Create an Account", color = Color.Blue)
        }
    }
}

@Composable
fun CustomTextField(
    value: MutableState<TextFieldValue>,
    label: String,
    isPassword: Boolean,
    icon: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .background(Color.Gray.copy(alpha = 0.1f))
                .border(1.dp, Color.Gray)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                modifier = Modifier.padding(end = 16.dp)
            )
            BasicTextField(
                value = value.value,
                onValueChange = { value.value = it },
                textStyle = TextStyle(fontFamily = Poppins, fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Label
        Text(
            text = label,
            style = TextStyle(
                fontFamily = Poppins,
                fontSize = 12.sp,
                color = Color.Gray
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun LoginWithButton(icon: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Button(
            onClick = { /* Implement login logic here */ },
            modifier = Modifier
                .weight(1f) // Berfungsi karena berada dalam Row
                .padding(4.dp)
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        onLoginClick = { /* Handle login click */ },
        onForgotPasswordClick = { /* Handle forgot password click */ },
        onSignUpClick = { /* Handle sign up click */ }
    )
}
