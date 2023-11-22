package com.dijonz.smartplants

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dijonz.smartplants.ui.theme.SmartPlantsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount() {

    val serverConnect = ServerConnect()

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var local by remember { mutableStateOf("") }
    var fotoUri by remember { mutableStateOf("") }
    Scaffold(

        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "SmartPlants",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Insira seus dados para criar uma nova conta de vendedor",
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 30.dp)
                )
                Image(
                    modifier = Modifier
                        .clip(CircleShape),
                    painter = painterResource(R.drawable._60_f_215844325_ttx9yiiiyear7ne6ealljmamy4gvpc69),
                    contentDescription = "Clique para adicionar uma foto",
                    contentScale = ContentScale.Crop
                )
                OutlinedTextField(
                    modifier = Modifier.width(320.dp),
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome") }
                )
                OutlinedTextField(
                    modifier = Modifier.width(320.dp),
                    value =email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") }
                )
                OutlinedTextField(
                    modifier = Modifier.width(320.dp),
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Senha") }
                )
                OutlinedTextField(
                    modifier = Modifier.width(320.dp),
                    value = telefone,
                    onValueChange = { telefone = it },
                    label = { Text("Telefone") }
                )
                OutlinedTextField(
                    modifier = Modifier.width(320.dp),
                    value = local,
                    onValueChange = { local = it },
                    label = { Text("Local") }
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        if (nome.isNotBlank() &&
                            email.isNotBlank() &&
                            senha.isNotBlank() &&
                            telefone.isNotBlank() &&
                            local.isNotBlank()
                        ) {
                            val vendedor = Vendedor(nome, email, senha, telefone, local, "enzo")
                            // Use a coroutine to perform the network operation in the background
                            GlobalScope.launch {
                                try {
                                    withContext(Dispatchers.IO) {
                                        serverConnect.enviaVendedor(vendedor)
                                    }
                                } catch (e: Exception) {
                                    // Handle the exception appropriately (e.g., show an error message)
                                    Log.e("CreateAccount", "Error sending Vendedor: ${e.message}", e)
                                }
                            }
                        }
                    },
                    modifier = Modifier.size(width = 260.dp, height = 50.dp)
                ) {
                    Text("Continuar")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCreateAccount() {
    SmartPlantsTheme {
        CreateAccount()
    }
}
