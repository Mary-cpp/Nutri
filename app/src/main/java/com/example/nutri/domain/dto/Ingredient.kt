package com.example.nutri.domain.dto

import com.example.nutri.domain.dto.Nutrient
import com.example.nutri.domain.dto.Quantity

data class Ingredient(
    val id: Int?,
    val name:String,
    val amount: Quantity,
    val nutrient: List<Nutrient>
)
