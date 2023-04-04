package com.example.nutri.ui.navigation

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.nutri.core.rememberAppState

@Composable
fun HostScreen(){
    val appState = rememberAppState()

    Log.w("HostScreen", "Draw Host Screen")

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigateToScreen = appState::navigateToMenuItem)
        }){
        NavigationGraph(
            navController = appState.navController,
            navigateToScreen = appState::navigateToMenuItem,
            navigateWithRecipe = appState::navigateWithRecipe,
            navigateBack = appState::navigateUpBackStack,
            paddingValues = it
        )
    }
}