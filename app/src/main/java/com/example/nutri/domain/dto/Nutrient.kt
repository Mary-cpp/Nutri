package com.example.nutri.domain.dto

data class Nutrient(
    val id: Int?,
    val name: String,
    val description: String,
    val amount: Quantity
) {
}