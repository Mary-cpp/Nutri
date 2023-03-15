package com.example.nutri.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    )]
)
class IngredientsInRecipe(
    @PrimaryKey
    @ColumnInfo(name = "id_recipe")
    val idRecipe: Int,
    @ColumnInfo(name = "id_ingredient")
    val idIngredient: Int,
    val amount: Double,
    val units: String,
    val calories: Long
)