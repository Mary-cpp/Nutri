package com.example.nutri.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.theme.NutriTheme

val menuItems = listOf(Screen.MyRecipes, Screen.Home, Screen.BMI)

@Composable
fun HomePage() {

    Surface(Modifier.fillMaxSize(),
    color = MaterialTheme.colors.background) {

    }
}



@Preview
@Composable
fun HomePagePreview(){
    NutriTheme {
        HomePage()
    }
}