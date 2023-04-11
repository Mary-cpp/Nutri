package com.example.nutri.data.recipe.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "nutrients_in_recipe",
    foreignKeys = [
        ForeignKey(
        RecipeEntity::class,
        parentColumns = ["id"],
        childColumns = ["recipe_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    ),
        ForeignKey(
        NutrientEntity::class,
        parentColumns = ["id"],
        childColumns = ["nutrient_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )],
    primaryKeys = ["recipe_id", "nutrient_id"]
)
class NutrientsInRecipe(
    @ColumnInfo(name = "recipe_id", index = true)
    val recipeId: String,
    @ColumnInfo(name = "nutrient_id", index = true)
    val nutrientId: Int,
    val quantity: Double,
    val unit: String,
)