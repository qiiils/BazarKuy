package com.example.bazarkuy.ui.Navigation

import DashboardViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bazarkuy.ui.BazarDetail.BazarDetailScreen
import com.example.bazarkuy.ui.dashboard.DashboardScreen

//import com.example.bazarkuy.ui.history.HistoryScreen
//import com.example.bazarkuy.ui.notifications.NotificationsScreen
//import com.example.bazarkuy.ui.profile.ProfileScreen

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object History : NavRoutes("history")
    object Notifications : NavRoutes("notifications")
    object Profile : NavRoutes("profile")
    object BazarDetail : NavRoutes("bazar_detail/{bazarId}") {
        fun createRoute(bazarId: Int) = "bazar_detail/$bazarId"
    }
}


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
    }
}
        // Other Routes
//        composable(NavRoutes.History.route) {
//            HistoryScreen()
//        }
//
//        composable(NavRoutes.Notifications.route) {
//            NotificationsScreen()
//        }
//
//        composable(NavRoutes.Profile.route) {
//            ProfileScreen()

//}