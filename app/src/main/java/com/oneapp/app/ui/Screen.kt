package com.oneapp.app.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Location : Screen("location")
}

