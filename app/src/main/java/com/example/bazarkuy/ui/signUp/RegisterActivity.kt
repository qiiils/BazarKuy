package com.example.bazarkuy.ui.signUp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.*
//import androidx.compose.material3.ExposedDropdownMenu
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.bazarkuy.ui.login.CustomTextField
import com.example.bazarkuy.ui.login.LoginActivity
import com.example.bazarkuy.R


class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen(
                onRegisterClick = { /* Handle register logic */ },
                onLoginClick = {
                    // Navigasi ke LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Menutup RegisterActivity agar tidak bisa kembali ke sini
                }
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegisterClick: () -> Unit, onLoginClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("") }
    val roles = listOf("Penyelenggara Bazar", "UMKM")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material3.Text(
            text = "Create an",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                color = Color.Black
            )
        )
        androidx.compose.material3.Text(
            text = "Account",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        // Input Username
        CustomTextField(
            value = username,
            onValueChange = { username = it },
            label = "Confirm Password",
            iconRes = R.drawable.ic_username,
            placeholder = "Enter your username"
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = password,
            onValueChange = { password = it},
            label = "Password",
            isPassword = true,
            iconRes = R.drawable.ic_password,
            placeholder = "Enter your password"
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it},
            label = "Password",
            isPassword = true,
            iconRes = R.drawable.ic_password,
            placeholder = "Enter your password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        RoleDropdown(
            roles = roles,
            selectedRole = selectedRole,
            onRoleSelected = { selectedRole = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Button(
                onClick = { onRegisterClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5D72E9)
                ),
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = "Register")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Link untuk kembali ke Login
        Box (
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TextButton(onClick = onLoginClick) {
                Text(
                    text = "Create an Account",
                    color = Color(0xFF5D72E9),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleDropdown(
    roles: List<String>,
    selectedRole: String,
    onRoleSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedRole,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Role") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF5D72E9),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF5D72E9),
                unfocusedLabelColor = Color.Gray
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            roles.forEach { role ->
                DropdownMenuItem(
                    text = { Text(text = role) },
                    onClick = {
                        onRoleSelected(role)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
