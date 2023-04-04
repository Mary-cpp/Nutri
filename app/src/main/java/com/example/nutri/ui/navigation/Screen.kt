package com.example.nutri.ui.navigation

import com.example.nutri.R

sealed class Screen(
    val title: String,
    val icon: Int? = null,
    val screenRoute: String
) {
    object Home : Screen("Home", R.drawable.home48px, "home")
    object MyRecipes : Screen("My Meals", R.drawable.my_recipes48px, "my_recipes")
    object BMI : Screen("My BMI", R.drawable.calculate48px, "bmi")
    object CreateRecipe : Screen("Create", screenRoute = "create_recipe_page")
    object EditRecipe : Screen ("Edit", screenRoute = "edit_recipe_page")
    object Recipe : Screen("Recipe", screenRoute = "recipe_details")
    object SearchPage : Screen("Search", screenRoute = "search_page")
}