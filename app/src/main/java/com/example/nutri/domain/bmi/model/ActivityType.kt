package com.example.nutri.domain.bmi.model

enum class ActivityType (
    var text: String,
    var index: Float
) {
    SEDENTARY(text = "Sedentary", index = 1.2f),
    LIGHT(text = "Light", index = 1.375f),
    MODERATE(text = "Moderate", index = 1.55f),
    VERY_ACTIVE(text = "Very active", index = 1.725f),
    EXTRA_ACTIVE(text = "Extra active", index = 1.9f);

    companion object {
        val entries: List<String> = listOf(
            "SEDENTARY",
            "LIGHT",
            "MODERATE",
            "VERY_ACTIVE",
            "EXTRA_ACTIVE"
        )
    }
}