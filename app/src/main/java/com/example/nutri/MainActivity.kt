package com.example.nutri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.nutri.ui.navigation.HostScreen
import com.example.nutri.ui.theme.NutriTheme
import com.example.nutri.ui.viewmodel.MyRecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = ViewModelProvider(this)[MyRecipesViewModel::class.java]

        setContent {
          NutriTheme {

                HostScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NutriTheme {
        HostScreen()
    }
}

