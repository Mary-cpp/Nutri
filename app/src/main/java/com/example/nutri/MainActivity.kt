package com.example.nutri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.BottomNavigationBar
import com.example.nutri.ui.navigation.NavigationGraph
import com.example.nutri.ui.theme.NutriTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject
    lateinit var navControllerHolder: NavControllerHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController: NavHostController = rememberNavController()

            navControllerHolder.navController = navController

            NutriTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) })
                {
                    NavigationGraph(
                      navController = navController,
                      paddingValues = it
                    )
                }
            }
        }
    }
}