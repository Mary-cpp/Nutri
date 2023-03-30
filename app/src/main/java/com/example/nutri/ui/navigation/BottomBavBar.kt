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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

val menuItems = listOf(Screen.MyRecipes, Screen.Home, Screen.BMI)

@Composable
fun BottomNavigationBar(navController: NavController) {

    val selectedItem = remember { mutableStateOf(1) }

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value

    val screensWithNoBottomBar = listOf(Screen.EditRecipe.screenRoute, Screen.Recipe.screenRoute)
    if (screensWithNoBottomBar.contains(currentDestination?.destination?.route)) return

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

                        currentDestination?.destination?.let {
                            if (it.route.equals(item.screenRoute)) return@BottomNavigationItem
                        }

                        selectedItem.value = index
                        navController.navigate(item.screenRoute) {
                            navController.setGraph(navController.graph, navController.saveState())
                            popUpTo(navController.graph.startDestinationRoute!!)
                            launchSingleTop = true
                        }
                    },
                    selectedContentColor = MaterialTheme.colors.primary
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}