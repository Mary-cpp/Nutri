package com.example.nutri.domain.bmi.model

enum class ActivityType (
    var text: String,
    var index: Float
) {
    SEDENTARY(text = "SEDENTARY", index = 1.2f),
    LIGHT(text = "LIGHT", index = 1.375f),
    MODERATE(text = "MODERATE", index = 1.55f),
    VERY_ACTIVE(text = "VERY_ACTIVE", index = 1.725f),
    EXTRA_ACTIVE(text = "EXTRA_ACTIVE", index = 1.9f);

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