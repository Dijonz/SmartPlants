package com.dijonz.smartplants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme


@Composable
fun StartScreen(
    navController: NavController
){
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 21.sp)){
            append("Bem-vindo ao\n\n")
        }
        withStyle(style = SpanStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)){
            append("SmartPlants")
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 50.dp)
        ) {

            Text(
                text = text,
                modifier = Modifier.padding(bottom = 450.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )

            Button(
                onClick = { navController.navigate(Screen.ProductsScreen.route) },
                modifier = Modifier.size(width = 260.dp, height = 50.dp)){
                Text("Visualizar Produtos")
            }

            Spacer(modifier = Modifier.height(15.dp))

            ElevatedButton(
                onClick = { navController.navigate(Screen.SignUpScreen.route) },
                modifier = Modifier.size(width = 260.dp, height = 50.dp)){
                Text(text = "Sou um Vendedor")
            }
        }
    }
}


@Preview
@Composable
fun PreviewStart(){
    SmartPlantsTheme {
        StartScreen(navController = rememberNavController())
    }
}