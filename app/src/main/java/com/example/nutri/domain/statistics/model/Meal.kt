package com.example.nutri.domain.statistics.model

import com.example.nutri.domain.recipes.model.Recipe

data class Meal (
    val name: String,
    val recipes: MutableList<Recipe>,
    var date: String
)