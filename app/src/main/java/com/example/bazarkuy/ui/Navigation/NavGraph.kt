package com.example.bazarkuy.ui.Navigation

import DashboardViewModel
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bazarkuy.ProfileScreen
import com.example.bazarkuy.ui.BazarDetail.BazarDetailScreen
import com.example.bazarkuy.ui.dashboard.DashboardScreen
import com.example.bazarkuy.ui.Notification.NotificationScreen
import com.example.bazarkuy.ui.Tracker.ListProgressScreen
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//import com.example.bazarkuy.ui.history.HistoryScreen
//import com.example.bazarkuy.ui.notifications.NotificationsScreen
//import com.example.bazarkuy.ui.profile.ProfileScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    dashboardViewModel: DashboardViewModel,
    userRole: String, // Tambahkan parameter userRole
    context: android.content.Context

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
            ListProgressScreen(
                navController = navController,
                userRole = userRole, // Pastikan userRole diteruskan
                context = context)
        }

        composable(Screen.Notifications.route) {
            NotificationScreen(navController = navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}