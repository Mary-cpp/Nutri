package com.example.nutri.data.entity

import androidx.room.*

@Entity(
    tableName = "Label",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_recipe"]
        )
    ]
)
class Label (

    @PrimaryKey(autoGenerate = true)
    val id:Int?,

    @ColumnInfo(name = "id_recipe")
    val recipeId: Int,

    val text: String,
    val category: String,
)