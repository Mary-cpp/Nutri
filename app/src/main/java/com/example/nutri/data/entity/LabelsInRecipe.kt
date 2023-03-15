package com.example.nutri.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_recipe")
    ),
        ForeignKey(
        entity = Label::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_label")
    )]
)
class LabelsInRecipe (
    @PrimaryKey
    @ColumnInfo(name = "id_recipe")
    val idRecipe: Int,
    @PrimaryKey
    @ColumnInfo(name = "id_label")
    val idLabel: Int
)