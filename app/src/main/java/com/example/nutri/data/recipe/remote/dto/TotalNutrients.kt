package com.example.nutri.data.recipe.remote.dto

import com.example.nutri.data.recipe.remote.dto.nutrients.*
import com.google.gson.annotations.SerializedName

data class TotalNutrients (
    val VITD: VITD?,
    @SerializedName("ENERC_KCAL")
    val ENERCKCAL: ENERC_KCAL?,
    val FASAT: FASAT?,
    @SerializedName("VITA_RAE")
    val VITARAE: VITA_RAE?,
    val PROCNT: PROCNT?,
    @SerializedName("TOCPHA")
    val TOPCHA: TOCPHA?,
    val CHOLE: CHOLE?,
    val CHOCDF: CHOCDF?,
    val FAT: FAT?,
    val FIBTG: FIBTG?,
    val RIBF: RIBF?,
    val THIA: THIA?,
    @SerializedName("FAPU")
    val FAPU: FAPU?,
    val NIA: NIA?,
    val VITC: VITC?,
    @SerializedName("FAMS")
    val FAMS: FAMS?,
    val VITB6A: VITB6A?,
    val VITB12: VITB12?,
    @SerializedName("WATER")
    val WATER: WATER?,
    val K: K?,
    val P: P?,
    val NA: NA?,
    val ZN: ZN?,
    @SerializedName("SUGAR")
    val SUGAR: SUGAR?,
    val CA: CA?,
    val MG: MG?,
    val FE: FE?,
    val VITK1: VITK1?,
    @SerializedName("FOLFD")
    val FOLFD: FOLFD?,
    @SerializedName("FOLAC")
    val FOLAC: FOLAC?,
    val FOLDFE: FOLDFE?
)