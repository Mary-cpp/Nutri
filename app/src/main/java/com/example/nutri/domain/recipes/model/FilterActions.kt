package com.example.nutri.domain.recipes.model

sealed class FilterActions {
    data class Diet(val dietFilter: DietFilter) : FilterActions()
    data class Caution(val cautions: Cautions) : FilterActions()
}

enum class DietFilter{
    VEGAN,
    GLUTEN_FREE,
    PORK_FREE,
    SOY_FREE,
    KIDNEY_FRIENDLY,
    DAIRY_FREE,
}

enum class Cautions