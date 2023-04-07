package com.example.nutri.data.recipe.remote.dto.nutrients

import com.google.gson.annotations.SerializedName

data class FAMS (
    override val label : String,
    override val quantity : Double,
    override val unit : String ) : Nutrient()

data class ENERC_KCAL (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FAPU (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FASAT (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FIBTG (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class SUGAR (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class PROCNT (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class CHOCDF (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class CHOLE (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class NA (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class CA (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class MG (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class K (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FAT (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FE (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class ZN (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class P (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()


data class VITA_RAE (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class VITC (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class THIA (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class RIBF (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class NIA (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class VITB6A (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FOLDFE (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FOLFD (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FOLAC (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class VITB12 (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class VITD (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class TOCPHA (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class VITK1 (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class WATER (
    @SerializedName("label")
    override val label : String,
    @SerializedName("quantity")
    override val quantity : Double,
    @SerializedName("unit")
    override val unit : String ): Nutrient()

data class PROCNT_KCAL (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class FAT_KCAL (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()

data class CHOCDF_KCAL (
    override val label : String,
    override val quantity : Double,
    override val unit : String ): Nutrient()
