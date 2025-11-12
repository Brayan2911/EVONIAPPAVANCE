package com.app.evoniapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.evoniapp.ui.theme.EvoniAPPTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvoniAPPTheme {
                EvoniApp()
            }
        }
    }
}

@Composable
fun EvoniApp() {
    val navController = rememberEvoniNavController()
    Scaffold(
        bottomBar = { if (shouldShowBottomBar(navController)) EvoniBottomBar(navController) }
    ) { padding ->
        EvoniNavHost(navController = navController, paddingValues = padding)
    }
}

@Composable
private fun shouldShowBottomBar(navController: NavHostController): Boolean {
    val entry by navController.currentBackStackEntryAsState()
    return when (entry?.destination?.route) {
        com.app.evoniapp.navigation.Route.Home.route,
        com.app.evoniapp.navigation.Route.Events.route,
        com.app.evoniapp.navigation.Route.Profile.route -> true
        else -> false
    }
}
