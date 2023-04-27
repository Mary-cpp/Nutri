package com.example.nutri.ui.screens.common

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun HomeScreenTopBar(topBarText: String, action: () -> Unit) {
    TopAppBar(
        title = { Text(text = topBarText, color = Color.Black) },
        actions = {
                  IconButton(onClick = { action.invoke()}, modifier = Modifier.fillMaxHeight()) {
                      Icon(
                          imageVector = ImageVector.vectorResource(id = com.example.nutri.R.drawable.edit_notifications_48px),
                          contentDescription = "NavigateToNotificationsConfigurations",
                          modifier = Modifier.size(24.dp),
                          tint = Color.Black
                      )
                  }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun HomeScreenTopBarPreview(){
    NutriTheme {
        HomeScreenTopBar(topBarText = "Home") {

        }
    }
}