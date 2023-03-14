package com.example.nutri.data.dto.nutrients

abstract class Nutrient(
    open val label: String,
    open val quantity: Double,
    open val unit: String
)