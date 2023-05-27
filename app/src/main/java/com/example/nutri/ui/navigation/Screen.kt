package com.example.nutri.ui.navigation

import com.example.nutri.R

sealed class Screen(
    val title: Int,
    val icon: Int? = null,
    val screenRoute: String
) {
    object Home : Screen(R.string.home, R.drawable.home48px, "home")
    object RecipesList : Screen(R.string.recipes_short, R.drawable.my_recipes48px, "my_recipes")
    object BMI : Screen(R.string.bmi_short, R.drawable.calculate48px, "bmi")
    object CreateRecipe : Screen(R.string.new_recipe, screenRoute = "create_recipe_page")
    object EditRecipe : Screen (R.string.edit_recipe, screenRoute = "edit_recipe_page")
    object Recipe : Screen(R.string.recipe, screenRoute = "recipe_details")
    object SearchPage : Screen(R.string.search, screenRoute = "search_page")
    object NotificationsConfigScreen : Screen(R.string.notifications, screenRoute = "notifications_config_page")
}