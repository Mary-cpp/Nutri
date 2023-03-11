package com.example.nutri.data.dto

data class Characteristics (
    val quantity: Double,
    val measure: String,
    val foodMatch: String,
    val food: String,
    val foodId: String,
    val weight: Double,
    val retainedWeight: Double,
    val nutrients: TotalNutrients?,
    val measureUri: String,
    val status: String
) {

    companion object{

        fun makeCharacteristics() : Characteristics{

            return Characteristics(
                quantity = 0.0,
                measure = "g",
                foodMatch = "some food match",
                food = "some food",
                foodId = "some food id",
                weight = 0.0,
                retainedWeight = 0.0,
                nutrients = null,
                measureUri = "measure uri",
                status = "status"
            )
        }
    }
}
