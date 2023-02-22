package com.example.nutri

import android.os.Bundle
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
import com.example.nutri.ui.recipe.viewmodel.RecipeListViewModel
import com.example.nutri.ui.theme.NutriTheme

class MainActivity : ComponentActivity() {

    lateinit var modelApi: RecipeListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

    var list by remember {
        mutableStateOf("")
    }

    var text by remember {
    mutableStateOf("")
}
    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            text = list,
            modifier = Modifier.padding(bottom = 20.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = {Text("ingredients")})

        Button(
            onClick = { list = text },
            modifier = Modifier.padding(top = 20.dp)
            )
            {
            Text("Analyze")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    NutriTheme {
        Greeting()
    }
}