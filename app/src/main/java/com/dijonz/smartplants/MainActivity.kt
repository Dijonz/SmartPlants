package com.dijonz.smartplants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartPlantsTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            StartScreen(navController = navController)
        }
        composable(route = Screen.ProductsScreen.route) {
            ProductsScreen(items = listOf(
                Item("Item1", "23"),
                Item("Item2", "43"),
                Item("Item3", "43"),
                Item("Item4", "43"),
                Item("Item5", "43")
            ))
        }
        composable(route = Screen.SignUpScreen.route){
            CreateAccount()
        }
    }
}

sealed class Screen(val route: String) {
    object MainScreen : Screen("start_screen")
    object SignUpScreen : Screen("signup_screen")
    object ProductsScreen : Screen("products_screen")
}
