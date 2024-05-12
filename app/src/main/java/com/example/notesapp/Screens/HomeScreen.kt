package com.example.notesapp.Screens

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController.Companion) {
    val textState = remember { mutableStateOf("") }
    val taskList = remember { mutableStateOf(mutableListOf<String>()) }
    val editIndex = remember { mutableStateOf(-1) }
    val editTextState = remember { mutableStateOf("") }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = textState.value,
                placeholder = { Text(text = "enter task...")},
                onValueChange = { newValue -> textState.value = newValue }
            )
            Text(text = textState.value)
                Button(
                    onClick = {
                        if (editIndex.value == -1){
                            taskList.value.add(textState.value)
                        } else {
                            taskList.value[editIndex.value] = textState.value
                            editIndex.value = -1
                        }


                        textState.value = ""
                    }

                ) {
                    Text(text = if (editIndex.value == -1) "ADD" else "EDIT")
                }
            taskList.value.forEachIndexed{ index, task ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    if (editIndex.value == index){
                        TextField(
                            value = editTextState.value,
                            onValueChange = { newValue -> editTextState.value = newValue}
                        )

                    }else {
                        Text(text = task)
                    }

                    Spacer(modifier = Modifier.width(8.dp),)
                    if (editIndex.value == index ) {
                        IconButton(
                            onClick = {
                               taskList.value[index] = editTextState.value
                                editIndex.value = -1
                                editTextState.value = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                editIndex.value = index
                                editTextState.value = task
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "EDIT"

                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = {
                                val updatedList = taskList.value.toMutableList()
                                updatedList.removeAt(index)
                                taskList.value = updatedList
                                //taskList.value.removeAt(index)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete")
                        }
                    }

                }

            }

        }
    }
}
