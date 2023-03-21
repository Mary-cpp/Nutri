package com.example.nutri.data.recipe.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "recipe_ingredients",
    foreignKeys = [
        ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = ["id"],
        childColumns = ["id_recipe"],
            onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
        entity = IngredientEntity::class,
        parentColumns = ["id"],
        childColumns = ["id_ingredient"],
            onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["id_recipe", "id_ingredient"]
)
class IngredientInRecipe(

    @ColumnInfo(name = "id_recipe", index = true)
    val idRecipe: Int,

    @ColumnInfo(name = "id_ingredient", index = true)
    val idIngredient: Int,

    val amount: Double,
    val units: String,
    val calories: Double
)