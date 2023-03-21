package com.example.nutri.data.bmi.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_types")
class ActivityTypeEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val text: String,
)
