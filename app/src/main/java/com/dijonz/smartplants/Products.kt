package com.dijonz.smartplants

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    items: ArrayList<Produto>
    ) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0,128,0),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Produtos")
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(800.dp)
                        .padding(vertical = 30.dp, horizontal = 10.dp)
                ) {
                    ProductsList(items = items)
                }
            }
        }

    }
}



@Composable
fun ProductsList(items: ArrayList<Produto>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(items){ item ->
                Card(item = item)
            }
        }

    )
}


@Composable
fun Card(item: Produto) {
    val title = item.nome
    val price = item.preco
    //val file = File(item.uri)
    //var foto: Uri? = null
    //println(foto.toString())

    var fotoUri by remember { mutableStateOf<Uri?>(Uri.parse("android.resource://com.dijonz.smartplants/"+ R.drawable.tomate_isolado)) }


    //if(item.uri==null){
        //foto = fotoUri
    //}

    Surface(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFEBF0FF),
                shape = RoundedCornerShape(size = 5.dp)
            )
            .width(165.dp)
            .height(282.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(133.dp)
                    .height(133.dp),
                model = fotoUri,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text ="R$:"+price+"/Kg",
                    fontSize = 21.sp
                )
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF223263),
                        letterSpacing = 0.5.sp,
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewProducts() {
    SmartPlantsTheme {
        ProductsScreen(
            items = ArrayList<Produto>()
        )
    }
}