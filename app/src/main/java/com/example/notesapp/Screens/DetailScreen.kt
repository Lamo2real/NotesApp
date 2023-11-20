package com.example.notesapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController) {
    Column (
        modifier = Modifier.background(Color.LightGray)
    ) {
        Text(text = "detail")
        Button(onClick = {
            navController.navigateUp()
        }) 
        {
            Text(text = "Go Back")
        }
    }
}