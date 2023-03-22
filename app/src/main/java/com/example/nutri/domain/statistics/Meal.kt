package com.example.nutri.domain.statistics

import com.example.nutri.domain.recipes.model.Recipe
import java.util.*

class Meal (
    val name: String,
    val recipes: List<Recipe>,
    val date: Date
)