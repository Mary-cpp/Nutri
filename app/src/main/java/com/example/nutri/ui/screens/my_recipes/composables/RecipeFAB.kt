package com.example.nutri.ui.screens.my_recipes.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
fun RecipeFAB(navController: NavController){
    FloatingActionButton(onClick = { navController.navigate(Screen.EditRecipe.screenRoute)},
        modifier = Modifier.size(56.dp),

        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(4.dp)) {

        Icon(
            ImageVector.vectorResource(id = R.drawable.add48px),
            contentDescription = "AddFAB",
            modifier = Modifier.size(24.dp),
            tint = Color.White)

    }
}