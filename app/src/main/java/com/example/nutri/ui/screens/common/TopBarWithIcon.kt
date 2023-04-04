package com.example.nutri.ui.screens.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.nutri.R

@Composable
fun TopBarWithIcon(topBarText: String, action: () -> Unit) {
    TopAppBar(title = { Text(text = topBarText, color = Color.Black) },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        navigationIcon = {

            IconButton(
                onClick = { action()},
                modifier = Modifier.padding(end = 6.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back48px),
                    contentDescription = "BackToListOfRecipes",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            }
        })
}