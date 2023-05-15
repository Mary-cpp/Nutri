package com.example.nutri.data.bmi.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "diet_plans",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        childColumns = ["user_id"],
        parentColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )]
)
class DietPlanEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    val kcal: Int,
    val proteins: Int? = null,
    val carbs: Int? = null,
    val fats: Int? = null,

    @ColumnInfo(name = "user_id", index = true)
    val userId: String
)