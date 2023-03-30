package com.example.nutri.domain.statistics

import com.example.nutri.domain.recipes.model.Recipe

class Meal (
    val name: String,
    val recipes: MutableList<Recipe>,
    var date: String
)