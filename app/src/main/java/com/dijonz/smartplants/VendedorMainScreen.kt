package com.dijonz.smartplants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme

@Composable
fun VendedorMain(
    navController: NavController
){
  Surface(
      modifier = Modifier.fillMaxSize()
  ) {
      Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
          Button(
              onClick = { navController.navigate(Screen.NewProduct.route) },
              modifier = Modifier.size(width = 260.dp, height = 50.dp)
          ) {
              Text("Adicionar novo Produto")
          }

          Spacer(modifier = Modifier.height(15.dp))

          Button(
              onClick = {  },
              modifier = Modifier.size(width = 260.dp, height = 50.dp)
          ) {
              Text("Minha Loja")
          }
      }
  }
}

@Preview
@Composable
fun PreviewScreen(){
    SmartPlantsTheme {
        VendedorMain(navController = rememberNavController())
    }
}
