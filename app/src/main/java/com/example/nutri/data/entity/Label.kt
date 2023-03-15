package com.example.nutri.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class Label (
    @PrimaryKey
    val id:Int,
    val text: String,
    val category: String
)
