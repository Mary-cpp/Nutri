package com.example.nutri.domain.bmi.model

enum class ExerciseType (
    var text: String
) {
    SEDENTARY("Sedentary"),
    LIGHT("Light"),
    MODERATE("Moderate"),
    ACTIVE("Active"),
    VERY_ACTIVE("Very active"),
    EXTRA_ACTIVE("Extra active");

    companion object {
        val entries: List<String> = listOf(
            "Sedentary",
            "Light",
            "Moderate",
            "Active",
            "Very active",
            "Extra active"
        )
    }
}