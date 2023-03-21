package com.example.nutri.data.recipe.local.entity

import androidx.room.*

@Entity(
    tableName = "recipe_labels",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_recipe"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Label::class,
            parentColumns = ["id"],
            childColumns = ["id_label"],
            onDelete = ForeignKey.CASCADE
        )],
    primaryKeys = ["id_recipe", "id_label"]
)
class LabelsInRecipe (

    @ColumnInfo(name = "id_recipe", index = true)
    val idRecipe: Int,

    @ColumnInfo(name = "id_label", index = true)
    val idLabel: Int,
)