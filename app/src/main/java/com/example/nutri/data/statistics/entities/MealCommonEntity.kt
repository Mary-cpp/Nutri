package com.example.nutri.data.statistics.entities

import androidx.room.Embedded
import androidx.room.Relation

class MealCommonEntity(

    @Embedded
    val meal: MealEntity,

    @Relation(
        entity = MealCategory::class,
        parentColumn = "id_category",
        entityColumn = "id")
    val mealCategory: MealCategory,

    @Relation(
        entity = RecipeInMeal::class,
        parentColumn = "id",
        entityColumn = "id_meal")
    val recipes: List<RecipeInMeal>
)