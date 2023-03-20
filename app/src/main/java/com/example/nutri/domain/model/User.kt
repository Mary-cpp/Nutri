package com.example.nutri.domain.model

class User (
    val sex: Char,
    val height: Float,
    val heightMeasure: String,
    val weight: Float,
    val weightMeasure: String,
    val age: Int,
    val plan: DietPlan
)