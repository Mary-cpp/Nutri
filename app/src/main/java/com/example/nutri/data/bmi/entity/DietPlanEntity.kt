package com.example.nutri.data.bmi.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diet_plans")
class DietPlanEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val kcal: Int,
    val proteins: Int,
    val carbs: Int,
    val fats: Int
)