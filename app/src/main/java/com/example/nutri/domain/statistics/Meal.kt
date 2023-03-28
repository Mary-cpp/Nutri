package com.example.nutri.domain.statistics

import com.example.nutri.domain.recipes.model.Recipe
import java.util.*

class Meal (
    val name: String,
    val recipes: MutableList<Recipe>,
    var date: Date
)