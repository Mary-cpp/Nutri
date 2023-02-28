package com.example.nutri.ui.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.recipe.viewmodel.RecipeAnalyzeViewModel

@Composable
fun Analyzer(viewModel: RecipeAnalyzeViewModel) {

    val expanded = remember { mutableStateOf(false) }

    val recipe by remember {
        viewModel.recipe
    }

    var text by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("ingredients") })

        Row(modifier = Modifier.padding(16.dp)){

            val context = LocalContext.current

            Button(
                onClick =
                {
                    viewModel.ingredient.value = text
                    viewModel.onAnalyzeButtonPressed(viewModel.ingredient.value)
                    expanded.value = !expanded.value}
            )
            {
                Text("Analyze")
            }

            Button(
                onClick =
                {
                    Toast
                        .makeText(context,
                            "Soon you'll be able to save this recipe",
                            Toast.LENGTH_SHORT)
                        .show()

                },
                modifier = Modifier.padding(start = 16.dp)
            )
            {
                Text("Save")
            }
        }

        if (expanded.value) RecipeDisplay(recipe = recipe)

    }
}