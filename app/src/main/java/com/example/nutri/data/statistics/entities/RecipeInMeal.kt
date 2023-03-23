package com.example.nutri.data.statistics.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.nutri.data.recipe.local.entity.RecipeEntity

@Entity(
    tableName = "recipes_in_meal",
    foreignKeys = [
        ForeignKey(
            entity = MealEntity::class,
            childColumns = ["id_meal"],
            parentColumns = ["id"]
        ),
        ForeignKey(
            entity = RecipeEntity::class,
            childColumns = ["id_recipe"],
            parentColumns = ["id"]
        )
    ],
    primaryKeys = ["id_meal", "id_recipe"]
)
class RecipeInMeal (

    @ColumnInfo(name = "id_meal", index = true)
    val idMeal: Int,

    @ColumnInfo(name = "id_recipe", index = true)
    val idRecipe: String
    )