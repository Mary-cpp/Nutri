package com.example.nutri.ui.compose

import android.widget.Toast
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
fun AnalyzerMainInterface(
    viewModel: RecipeAnalyzeViewModel,
    expandAnalysis: MutableState<Boolean>,
    expandSavedRecipes: MutableState<Boolean>)
{
    var text by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("ingredients") })

    Row(modifier = Modifier.padding(16.dp)){

        val context = LocalContext.current

        Button(
            onClick =
            {
                viewModel.onAnalyzeButtonPressed(text)
                expandAnalysis.value = text.isNotEmpty()
                }
        )
        {
            Text("Analyze")
        }

        Button(
            onClick =
            {
                viewModel.onSaveButtonPressed()

                expandSavedRecipes.value = true

                Toast
                    .makeText(context,
                        "Analysis was saved to recipe",
                        Toast.LENGTH_SHORT)
                    .show()
            },
            modifier = Modifier.padding(start = 16.dp)
        )
        {
            Text("Save")
        }

        Button(onClick = {
            viewModel.onMyRecipesButtonPressed()
            expandAnalysis.value = false
                         },
            modifier = Modifier.padding(start = 16.dp)) {
            Text ("My Recipes")
        }
    }
}