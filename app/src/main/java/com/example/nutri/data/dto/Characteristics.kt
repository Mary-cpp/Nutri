package com.example.nutri.data.dto

data class Characteristics (
    val quantity: Double,
    val measure: String,
    val foodMatch: String,
    val food: String? = null,
    val foodId: String? = null,
    val weight: Double? = null,
    val retainedWeight: Double? = null,
    val nutrients: TotalNutrients? = null,
    val measureUri: String? = null,
    val status: String? = null
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
