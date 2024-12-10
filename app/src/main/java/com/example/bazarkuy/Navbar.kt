package com.example.bazarkuy

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomNavbar(
    onHomeClick: () -> Unit,
    onAddClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    BottomAppBar(
        backgroundColor = Color.White,
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Home Button
            IconButton(onClick = onHomeClick) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                    Text(text = "Home", style = MaterialTheme.typography.body2)
                }
            }

            // Add Button
            IconButton(onClick = onAddClick) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    Text(text = "Add", style = MaterialTheme.typography.body2)
                }
            }

            // Notifications Button
            IconButton(onClick = onNotificationClick) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
                    Text(text = "Notifications", style = MaterialTheme.typography.body2)
                }
            }

            // Profile Button
            IconButton(onClick = onProfileClick) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
                    Text(text = "Profile", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomNavbar() {
    CustomNavbar(
        onHomeClick = {},
        onAddClick = {},
        onNotificationClick = {},
        onProfileClick = {}
    )
}
