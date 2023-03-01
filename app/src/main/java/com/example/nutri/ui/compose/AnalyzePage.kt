package com.example.nutri.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.recipe.viewmodel.RecipeAnalyzeViewModel

@Composable
fun Analyzer (viewModel: RecipeAnalyzeViewModel) {

    val expanded = remember { mutableStateOf(false) }

    val recipe by remember {
        viewModel.recipe
    }

    Column(modifier = Modifier.padding(20.dp)) {

        AnalyzerMainInterface(viewModel = viewModel, expanded = expanded)

        if (expanded.value) RecipeDisplay(recipe = recipe)

    }
}