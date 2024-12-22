package com.example.bazarkuy.Navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object BazarDetail : Screen("bazar/{bazarId}") {
        fun createRoute(bazarId: Int) = "bazar/$bazarId"
    }
}