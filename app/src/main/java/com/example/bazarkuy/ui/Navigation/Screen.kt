package com.example.bazarkuy.ui.Navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object History : Screen("history")
    object Notifications : Screen("notifications")
    object Profile : Screen("profile")
    object BazarDetail : Screen("bazar_detail/{bazarId}") {
        fun createRoute(bazarId: Int) = "bazar_detail/$bazarId"
    }
}