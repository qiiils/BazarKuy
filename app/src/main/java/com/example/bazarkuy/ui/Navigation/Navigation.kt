package com.example.bazarkuy.ui.Navigation

// BottomNavigation.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bazarkuy.R

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Int
)

@Composable
fun CustomBottomNavigation(
    navController: NavController,
    items: List<BottomNavItem> = listOf(
        BottomNavItem("Home", "home", R.drawable.ic_home),
        BottomNavItem("History", "history", R.drawable.ic_history),
        BottomNavItem("Notifications", "notifications", R.drawable.ic_notifications),
        BottomNavItem("Profile", "profile", R.drawable.ic_profile)
    )
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        containerColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = if (selected) {
                            Modifier
                                .shadow(
                                    elevation = 4.dp,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        } else {
                            Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name,
                            tint = if (selected) Color(0xFF4B6BFF) else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        if (selected) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.name,
                                color = Color(0xFF4B6BFF),
                                style = MaterialTheme.typography.labelSmall,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clickable {
                                    navController.navigate(item.route) {
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route) {
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}