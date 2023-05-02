package com.example.nutri.domain.statistics

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "water")
class Water (
    @PrimaryKey
    val date: Date,
    val amount: Int = 0
)