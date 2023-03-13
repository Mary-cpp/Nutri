package com.example.nutri.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.theme.NutriTheme

val menuItems = listOf(Screen.MyRecipes, Screen.Home, Screen.BMI)

@Composable
fun HomePage(navController : NavController) {
    Scaffold(bottomBar = { BottomNavigationBar(navController = navController)}) {
        paddingValues ->  Surface(Modifier.fillMaxSize().padding(paddingValues),
        color = MaterialTheme.colors.background) {
    }

    }


}



@Preview
@Composable
fun HomePagePreview(){
    NutriTheme {
        HomePage(rememberNavController())
    }
}