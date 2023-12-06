package com.dijonz.smartplants

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContent {
            SmartPlantsTheme {

                val serverConnect = ServerConnect()
                var isLoading by remember { mutableStateOf(true) }
                var productsList by remember { mutableStateOf(ArrayList<Produto>())}


                LaunchedEffect(Unit) {
                    if(isLoading){
                        withContext(Dispatchers.IO){
                            try {
                                val nullableProductsList: ArrayList<Produto?>? = serverConnect.returnAllProdutos()

                                productsList = ArrayList(nullableProductsList?.filterNotNull() ?: emptyList())
                                println("$productsList")
                            }
                            catch(e: Exception){
                                Log.e("MainActivity", "Erro ao buscar produtos", e)
                            }
                        }
                        isLoading = false
                    }
                }
                
                if (isLoading){
                    LoadingScreen()
                }
                else{
                    Navigation(productsList)
                }
            }
        }
    }
}


@Composable
fun Navigation(lista: ArrayList<Produto>) {
    val navController = rememberNavController()



    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            StartScreen(navController = navController)
        }
        composable(route = Screen.ProductsScreen.route) {
            ProductsScreen(items = lista)
        }
        composable(route = Screen.SignUpScreen.route) {
            CreateAccount(navController = navController)
        }
        composable(route = Screen.VendedorMain.route) {
            VendedorMain(navController = navController)
        }
        composable(route = Screen.NewProduct.route) {
            CreateProduct(navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    object MainScreen : Screen("start_screen")
    object SignUpScreen : Screen("signup_screen")
    object ProductsScreen : Screen("products_screen")
    object VendedorMain : Screen("vendedor_main")
    object NewProduct : Screen("new_product")
}
