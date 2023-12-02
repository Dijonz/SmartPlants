package com.dijonz.smartplants

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProduct(
    navController: NavController
) {


    var desc: String by remember { mutableStateOf("") }
    var price: String by remember { mutableStateOf("") }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.inversePrimary
                ),
                title = {
                    Text(text = "Vender novo produto")
                })
        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
        ) {
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier.size(height = 30.dp, width = 300.dp),
                    value = desc,
                    onValueChange = { desc = it },
                    label = { Text("O que você deseja vender?") }
                )
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.tomate_isolado),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                TextField(
                    modifier = Modifier
                        .size(height = 30.dp, width = 200.dp)
                        .padding(top = 100.dp),
                    value = price,
                    onValueChange = {price = it},
                    label = {Text("Preço")}
                    )
                Button(
                    onClick = { navController.navigate(Screen.VendedorMain.route) },
                    modifier = Modifier.padding(top = 200.dp)
                ){
                    Text("Cadastrar Produto")
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview(){
    SmartPlantsTheme {
        CreateProduct(navController = rememberNavController())
    }
}

