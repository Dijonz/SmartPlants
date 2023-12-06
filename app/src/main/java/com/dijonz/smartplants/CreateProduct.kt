package com.dijonz.smartplants

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProduct(
    navController: NavController
) {


    var desc: String by remember { mutableStateOf("") }
    var price: String by remember { mutableStateOf("") }
    var fotoUri by remember { mutableStateOf<Uri?>(Uri.parse("android.resource://com.dijonz.smartplants/"+ R.drawable.miroodles___sticker)) }


    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { fotoUri = it })


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
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .size(width = 330.dp, height = 500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.width(320.dp),
                    text = "toque na melancia para adicionar uma foto do seu produto",
                    fontStyle = FontStyle.Italic
                )
                AsyncImage(
                    modifier = Modifier.size(250.dp)
                        .size(width = 200.dp, height = 200.dp)
                        .padding(10.dp)
                        .clickable {
                            singlePhotoPicker.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    model = fotoUri
                )
                TextField(
                    modifier = Modifier.width(320.dp),
                    value = desc,
                    onValueChange = { desc = it },
                    label = { Text("O que você deseja vender?") }
                )
                OutlinedTextField(
                    modifier = Modifier.width(320.dp),
                    value = price,
                    onValueChange = {price = it},
                    label = {Text("Digite o preço em reais")}
                    )
            }
            Box(
                modifier = Modifier.padding(30.dp),
                contentAlignment = Alignment.BottomEnd
            ){
                Button(
                    onClick = {
                        navController.navigate(Screen.VendedorMain.route)
                        val uriString = fotoUri.toString()
                        val produto =  Produto(desc, price, uriString)
                        ServerConnect().enviaProduto(produto)},
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

