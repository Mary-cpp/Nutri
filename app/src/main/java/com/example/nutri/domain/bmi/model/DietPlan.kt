package com.example.nutri.domain.bmi.model

class DietPlan(
    val kcal: Int,
    val proteins: Float,
    val carbs: Float,
    val fats: Float
){
    override fun equals(other: Any?): Boolean {
        if (other !is DietPlan) return false
        if (other.kcal != kcal || other.carbs != carbs || other.proteins != proteins || other.fats != fats) return false
        return true
    }
}
