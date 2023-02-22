package com.example.nutri.domain.dto

data class Quantity (
    val id: Int,
    val value: Double,
    val unit: String
        ) {

    companion object {
        val empty = Quantity(0, 0.0,  "g")

    }
}