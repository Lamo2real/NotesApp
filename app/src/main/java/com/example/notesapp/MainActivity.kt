package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.Screens.DetailScreen
import com.example.notesapp.Screens.HomeScreen
import com.example.notesapp.Screens.Screen
import com.example.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var showBackButton by rememberSaveable {
                mutableStateOf(false)
            }
            var topBarTitle by rememberSaveable {
                mutableStateOf("Home")
            }

            NotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                                title = {
                                Text(text = topBarTitle)
                            },
                                navigationIcon = {
                                    if (showBackButton){
                                        IconButton(onClick = {
                                            navController.navigateUp()
                                        }) {
                                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                        }
                                    } else {
                                        null
                                    }

                                }
                            )
                        }
                    ){ innerPadding ->
                        Box(
                            modifier = Modifier.padding(innerPadding)
                        ){
                            NavHost(navController = navController, startDestination = Screen.Home.route) {
                                composable(route = Screen.Home.route) {
                                    showBackButton = false
                                    topBarTitle = "Home"
                                    HomeScreen(navController = navController)

                                }
                                composable(route = Screen.Detail.route + "/{name}") {navBackStackEntry ->
                                    showBackButton = true
                                    topBarTitle = "Detail"
                                    val name = navBackStackEntry.arguments?.getString("name") ?: ""
                                    DetailScreen(name = name, navController = navController )
                                }
                            }
                        }

                    }

                }
            }
        }
    }
}
