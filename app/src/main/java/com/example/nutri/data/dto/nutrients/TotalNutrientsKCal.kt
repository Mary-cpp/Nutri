package com.example.nutri.data.dto.nutrients

import com.google.gson.annotations.SerializedName

data class TotalNutrientsKCal (
    @SerializedName("ENERC_KCAL")
    val ENERCKCAL: ENERC_KCAL,
    @SerializedName("PROCNT_KCAL")
    val PROCNTKCAL: PROCNT_KCAL,
    @SerializedName("FAT_KCAL")
    val FATKCAL: FAT_KCAL,
    @SerializedName("CHOCDF_KCAL")
    val CHOCDFKCAL: CHOCDF_KCAL
        )