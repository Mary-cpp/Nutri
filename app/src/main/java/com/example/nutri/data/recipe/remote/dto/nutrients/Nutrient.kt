package com.example.nutri.data.recipe.remote.dto.nutrients

import com.google.gson.InstanceCreator
import java.lang.reflect.Type

interface Nutrient{
    val label: String
    val quantity: Double
    val unit: String
}

open class BaseNutrient: Nutrient {
    override val label: String = "1"
    override val quantity: Double = 0.0
    override val unit: String = "2"

    override fun toString(): String {
        return "$label $quantity $unit"
    }
}

class BaseNutrientInstanceCreator : InstanceCreator<BaseNutrient>{
    override fun createInstance(type: Type?): BaseNutrient {
        return BaseNutrient()
    }
}