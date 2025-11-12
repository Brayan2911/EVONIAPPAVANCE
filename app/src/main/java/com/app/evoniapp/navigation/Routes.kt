package com.app.evoniapp.navigation

sealed class Route(val route: String) {
    object Splash : Route("splash")
    object Login : Route("login")
    object Register : Route("register")
    object Home : Route("home")

    object Events : Route("events")
    object Profile : Route("profile")
}
