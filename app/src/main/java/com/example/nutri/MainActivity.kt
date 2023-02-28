package com.example.nutri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.nutri.ui.compose.Analyzer
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NutriTheme {
        Analyzer(viewModel = RecipeAnalyzeViewModel())
    }
}
