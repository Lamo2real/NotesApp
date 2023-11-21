package com.example.notesapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var showError by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "home")
        OutlinedTextField(value = text, onValueChange = {
            text = it
        })
        if (showError) {
            Text(text = "You  Must Enter Name")
        }
        Button(onClick = {
            if (text.isNotEmpty()){
                navController.navigate(Screen.Detail.route + "/$text")
            } else {
                 showError = true
            }

        }) {
            Text(text = "Go To DetailScreen")
        }
    }
}
