package com.example.nutri.data.recipe.remote.dto.nutrients

import com.google.gson.InstanceCreator
import java.lang.reflect.Type

interface Nutrient{
    val label: String
    val quantity: Double
    val unit: String
}

open class BaseNutrient: Nutrient {
    override var label: String = "1"
    override var quantity: Double = 0.0
    override var unit: String = "2"

    override fun toString(): String {
        return "$label $quantity $unit"
    }

    fun setFields(label: String, quantity: Double, unit: String) : BaseNutrient{
        this.label = label
        this.quantity = quantity
        this.unit = unit

        return this
    }
}

class BaseNutrientInstanceCreator : InstanceCreator<BaseNutrient>{
    override fun createInstance(type: Type?): BaseNutrient {
        return BaseNutrient()
    }
}