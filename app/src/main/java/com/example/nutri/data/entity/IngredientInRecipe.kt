package com.example.nutri.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    foreignKeys = [
        ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = ["id"],
        childColumns = ["id_recipe"]
    ),
        ForeignKey(
        entity = IngredientEntity::class,
        parentColumns = ["id"],
        childColumns = ["id_ingredient"]
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
    val calories: Long
)