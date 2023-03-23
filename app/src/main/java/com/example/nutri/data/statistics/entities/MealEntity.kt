package com.example.nutri.data.statistics.entities

import androidx.room.*
import java.util.*

@Entity(
    tableName = "meals",
    foreignKeys = [
        ForeignKey(
            entity = MealCategory::class,
            childColumns = ["id_category"],
            parentColumns = ["id"]
        )
    ],
    indices = [Index(value = ["id"], unique = true)]
)
class MealEntity (

    val id: String,

    @PrimaryKey
    val db_id: Int?,

    @ColumnInfo("id_category", index = true)
    val idCategory: String,
    val date: Date
    )