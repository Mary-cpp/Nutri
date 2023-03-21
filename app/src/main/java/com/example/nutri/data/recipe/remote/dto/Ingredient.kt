package com.example.nutri.data.recipe.remote.dto

data class Ingredient(
    val text: String,
    val parsed: List<Characteristics>?
) {
}
