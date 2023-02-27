package com.example.nutri.domain.dto

data class Ingredient(
    val text: String,
    val parsed: List<Characteristics>
) {
}
