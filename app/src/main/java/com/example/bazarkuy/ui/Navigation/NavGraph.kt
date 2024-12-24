package com.example.bazarkuy.ui.Navigation

import DashboardViewModel
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bazarkuy.ui.BazarDetail.BazarDetailScreen
import com.example.bazarkuy.ui.dashboard.Dashboard
import com.example.bazarkuy.ui.dashboard.DashboardScreen

//import com.example.bazarkuy.ui.history.HistoryScreen
//import com.example.bazarkuy.ui.notifications.NotificationsScreen
//import com.example.bazarkuy.ui.profile.ProfileScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    dashboardViewModel: DashboardViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                viewModel = dashboardViewModel,
                onBazarClick = { bazarId ->
                    navController.navigate(Screen.BazarDetail.createRoute(bazarId))
                }
            )
        }

        composable(
            route = Screen.BazarDetail.route,
            arguments = listOf(navArgument("bazarId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bazarId = backStackEntry.arguments?.getInt("bazarId") ?: return@composable
            BazarDetailScreen(
                bazarId = bazarId,
                onBackClick = { navController.navigateUp() }
            )
        }

        composable(Screen.History.route) {
            // Add your History screen here
            Text("History Screen")
        }

        composable(Screen.Notifications.route) {
            // Add your Notifications screen here
            Text("Notifications Screen")
        }

        composable(Screen.Profile.route) {
            // Add your Profile screen here
            Text("Profile Screen")
        }
    }
}