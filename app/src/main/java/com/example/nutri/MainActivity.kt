package com.example.nutri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nutri.ui.navigation.HostScreen
import com.example.nutri.ui.navigation.Screen
import com.example.nutri.ui.theme.NutriTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

          NutriTheme {
              /*val navController = rememberNavController()
              NavHost(
                  navController = navController,
                  startDestination = Screen.Home.screenRoute){
              }*/
                HostScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NutriTheme {
        //HostScreen()
    }
}

