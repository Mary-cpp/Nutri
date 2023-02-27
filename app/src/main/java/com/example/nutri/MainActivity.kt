package com.example.nutri

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.nutri.ui.recipe.viewmodel.RecipeAnalyzeViewModel
import com.example.nutri.ui.theme.NutriTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriTheme {

                val vm: RecipeAnalyzeViewModel = ViewModelProvider(this)[RecipeAnalyzeViewModel::class.java]
                vm.recipe

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Analyzer(vm)
                }
            }
        }
    }
}

@Composable
fun Analyzer(viewModel: RecipeAnalyzeViewModel) {

    val recipe = viewModel.recipe.value

    var list by remember {
        mutableStateOf("Hello")
    }

    var text by remember {
    mutableStateOf("")
}
    Column(modifier = Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = {Text("ingredients")})

        Button(
            onClick =
            { list = recipe
                viewModel.ingr.value = text
                Log.w("Compose", "Mutable analyzed string: $recipe")},
            modifier = Modifier.padding(top = 20.dp)
            )
            {
            Text("Analyze")
        }

        Text(
            text = list,
            modifier = Modifier.padding(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NutriTheme {
        Analyzer(viewModel = RecipeAnalyzeViewModel())
    }
}
