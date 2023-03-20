package com.example.nutri.domain.model

import com.example.nutri.domain.bmi.model.DietPlan

class User (
    val sex: Char,
    val height: Float,
    val heightMeasure: String,
    val weight: Float,
    val weightMeasure: String,
    val age: Int,
    val plan: DietPlan? = null,
    val exerciseType: ExerciseType = ExerciseType.LIGHT
)

enum class ExerciseType {
    SEDENTARY,
    LIGHT,
    MODERATE,
    ACTIVE,
    VERY_ACTIVE,
    EXTRA_ACTIVE
}