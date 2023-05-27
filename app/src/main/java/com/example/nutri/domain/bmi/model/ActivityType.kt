package com.example.nutri.domain.bmi.model

import com.example.nutri.R

enum class ActivityType (
    var text: Int,
    var index: Float
) {
    SEDENTARY(text = R.string.type_sedentary, index = 1.2f),
    LIGHT(text = R.string.type_light, index = 1.375f),
    MODERATE(text = R.string.type_moderate, index = 1.55f),
    VERY_ACTIVE(text = R.string.type_very_active, index = 1.725f),
    EXTRA_ACTIVE(text = R.string.type_extra_active, index = 1.9f);

    companion object {
        val entries: List<Int> = listOf(
            R.string.type_sedentary,
            R.string.type_light,
            R.string.type_moderate,
            R.string.type_very_active,
            R.string.type_extra_active
        )
    }
}