package com.dijonz.smartplants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(contentAlignment = Alignment.Center){
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    }
}

@Composable
@Preview
fun PreviewLoading(){
    LoadingScreen()
}
