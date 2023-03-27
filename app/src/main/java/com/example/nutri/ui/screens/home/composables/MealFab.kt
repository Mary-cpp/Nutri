package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutri.R
import com.example.nutri.ui.navigation.Screen

@Composable
fun MealFAB(
    navController: NavController
){

    FloatingActionButton(
        onClick = {
            navController.navigate(Screen.SearchPage.screenRoute)
        },
        modifier = Modifier.size(56.dp),

        backgroundColor = Color.Black,
        elevation = FloatingActionButtonDefaults.elevation(4.dp)) {

        Icon(
            ImageVector.vectorResource(id = R.drawable.add48px),
            contentDescription = "AddFAB",
            modifier = Modifier.size(24.dp),
            tint = Color.White)
    }
}