package com.dijonz.smartplants

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme

val text = "text"


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    items: List<Item>
    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
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
                        .height(600.dp)
                        .padding(vertical = 30.dp, horizontal = 10.dp)
                ) {
                    ProductsList(items = items)
                }
            }
        }

    }
}


data class Item(val title: String, val price: String)

@Composable
fun ProductsList(items: List<Item>){
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
fun Card(item: Item) {
    val title = item.title
    val price = item.price

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
            Image(
                modifier = Modifier
                    .width(133.dp)
                    .height(133.dp),
                painter = painterResource(id = R.drawable.tomate_isolado),
                contentDescription = null,
                contentScale = ContentScale.Crop
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
            Text(
                text = price
            )
        }
    }
}

@Preview
@Composable
fun PreviewProducts() {
    SmartPlantsTheme {
        ProductsScreen(
            items = listOf(
                Item("Item1", "23"),
                Item("Item2", "43"),
                Item("Item3", "43"),
                Item("Item4", "43"),
                Item("Item5", "43")
            )
        )
    }
}