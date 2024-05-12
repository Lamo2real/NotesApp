package com.example.notesapp.viewModel

import android.widget.EditText
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class HandleTask {

    val textState: MutableState<String> = mutableStateOf("add task...")

    val currentValue = textState.value


}