package com.example.nutri.data.bmi.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "users",
foreignKeys = [
    ForeignKey(
        entity = ActivityTypeEntity::class,
        childColumns = ["activity_type_id"],
        parentColumns = ["id"],
        onDelete = ForeignKey.SET_NULL
    )
])
class UserEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,

    val height: Float,
    val heightUnit: String,
    val weight: Float,
    val weightUnit: String,
    val age: Int,
    val sex: Char,

    @ColumnInfo(name = "activity_type_id", index = true, defaultValue = 0.toString())
    val activityTypeId: Int
)