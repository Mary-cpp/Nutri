package com.example.nutri.ui.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.recipe.viewmodel.RecipeAnalyzeViewModel

@Composable
fun AnalyzerMainInterface(
    viewModel: RecipeAnalyzeViewModel)
{
    var text by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("ingredients") })

    Row(modifier = Modifier.padding(16.dp)){

        Button(
            onClick =
            {
                viewModel.onAnalyzeButtonPressed(text)
                }
        )
        {
            Text("Analyze")
        }
    }
}