package com.example.nutri.data.recipe.remote.dto.nutrients

abstract class Nutrient{
    abstract val label: String
    abstract val quantity: Double
    abstract val unit: String
}