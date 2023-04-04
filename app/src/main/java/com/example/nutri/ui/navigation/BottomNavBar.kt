package com.example.nutri.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    navigateToScreen: (String) -> Unit
) {
    val menuItems = listOf(Screen.MyRecipes, Screen.Home, Screen.BMI)
    val selectedItem = remember { mutableStateOf(1) }

    BottomNavigation(
        content = {

            menuItems.forEachIndexed { index, item ->
                BottomNavigationItem(
                    modifier = Modifier.padding(top = 8.dp),
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = item.icon!!),
                            modifier = Modifier.size(24.dp),
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = MaterialTheme.typography.caption.fontSize
                        )
                    },
                    selected = selectedItem.value == index,
                    onClick = {
                        selectedItem.value = index
                        navigateToScreen(item.screenRoute)
                    },
                    selectedContentColor = MaterialTheme.colors.primary
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}