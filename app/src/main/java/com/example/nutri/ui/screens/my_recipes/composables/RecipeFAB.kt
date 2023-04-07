package com.example.nutri.ui.screens.my_recipes.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun RecipeFAB(
    goToScreen: () -> Unit
){
    ExtendedFloatingActionButton(
        onClick = { goToScreen()},
        text = { Text(text = "New Recipe", fontSize = MaterialTheme.typography.subtitle2.fontSize) },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(4.dp),
        icon = {
            Icon(
                ImageVector.vectorResource(id = R.drawable.add48px),
                contentDescription = "AddFAB",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    )
}

@Composable
fun RecipeFABTest(){
    ExtendedFloatingActionButton(
        onClick = { },
        text = { Text(text = "New Recipe", fontSize = MaterialTheme.typography.subtitle2.fontSize) },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(4.dp),
        icon = {
            Icon(
                ImageVector.vectorResource(id = R.drawable.add48px),
                contentDescription = "AddFAB",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    )
}

@Preview
@Composable
fun RecipeFABPreview(){
    NutriTheme {
        RecipeFABTest()
    }
}