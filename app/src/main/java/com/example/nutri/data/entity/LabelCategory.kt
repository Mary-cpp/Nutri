package com.example.nutri.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = LabelCategory::class,
    parentColumns = arrayOf("categoryId"),
    childColumns = arrayOf("id"),
    onDelete = ForeignKey.NO_ACTION
)])
class LabelCategory(
    @PrimaryKey
    val id: Int,
    val name: String
)