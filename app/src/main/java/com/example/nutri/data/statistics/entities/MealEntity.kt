package com.example.nutri.data.statistics.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "meals",
    foreignKeys = [
        ForeignKey(
            entity = MealCategory::class,
            childColumns = ["id_category"],
            parentColumns = ["id"]
        )
    ]
)
class MealEntity (

    @PrimaryKey
    val id: Int?,

    @ColumnInfo("id_category", index = true)
    val idCategory: Int,
    val date: Long
    )