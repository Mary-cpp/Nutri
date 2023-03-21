package com.example.nutri.domain.bmi.model

class User (
    val sex: Char,
    val height: Float,
    val heightMeasure: String,
    val weight: Float,
    val weightMeasure: String,
    val age: Int,
    var plan: DietPlan? = null,
    val exerciseType: ExerciseType = ExerciseType.LIGHT
)