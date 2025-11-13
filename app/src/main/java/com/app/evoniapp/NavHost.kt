package com.app.evoniapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.evoniapp.navigation.Route
import com.app.evoniapp.ui.screens.*

@Composable
fun EvoniNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Route.Splash.route
    ) {

        composable(Route.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Route.Login.route) {
                        popUpTo(Route.Splash.route) { inclusive = true }
                    }
                }
            )
        }


        composable(Route.Login.route) {
            LoginScreen(
                onLogin = {
                    navController.navigate(Route.Home.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onRegister = { navController.navigate(Route.Register.route) }
            )
        }


        composable(Route.Register.route) {
            RegisterScreen(
                onRegistered = {
                    navController.navigate(Route.Home.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        // Home
        composable(Route.Home.route) {
            HomeScreen(
                onOpenTasks = { /* TODO: Tareas */ },
                onOpenGuests = { /* TODO: Invitados */ },
                onOpenBudget = { /* TODO: Presupuesto */ },
                onOpenEvents = { /* TODO: Evento */ },
                onOpenSettings = { /* TODO: Ajustes */ },
                onOpenProfile = { /* TODO: Perfil */ }
            )
        }


    }
}

//Holaaaaaaaa