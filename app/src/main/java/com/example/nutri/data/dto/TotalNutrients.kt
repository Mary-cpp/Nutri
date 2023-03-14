package com.example.nutri.data.dto

import com.example.nutri.data.dto.nutrients.*

data class TotalNutrients (
    val VITD: VITD?,
    val ENERCKCAL: ENERC_KCAL?,
    val FASAT: FASAT?,
    val VITARAE: VITA_RAE?,
    val PROCNT: PROCNT?,
    val TOPCHA: TOCPHA?,
    val CHOLE: CHOLE?,
    val CHOCDF: CHOCDF?,
    val FAT: FAT?,
    val FIBTG: FIBTG?,
    val RIBF: RIBF?,
    val THIA: THIA?,
    val FAPU: FAPU?,
    val NIA: NIA?,
    val VITC: VITC?,
    val FAMS: FAMS?,
    val VITB6A: VITB6A?,
    val VITB12: VITB12?,
    val WATER: WATER?,
    val K: K?,
    val P: P?,
    val NA: NA?,
    val ZN: ZN?,
    val SUGAR: SUGAR?,
    val CA: CA?,
    val MG: MG?,
    val FE: FE?,
    val VITK1: VITK1?,
    val FOLFD: FOLFD?,
    val FOLAC: FOLAC?,
    val FOLDFE: FOLDFE?
){
    fun mapToListOfNutrients() : List<Nutrient>{

        val listOfNutrients:  MutableList<Nutrient> = arrayListOf()

        this.VITD?.let { listOfNutrients.add(it) }
        this.ENERCKCAL?.let { listOfNutrients.add(it) }
        this.FASAT?.let { listOfNutrients.add(it) }
        this.VITARAE?.let { listOfNutrients.add(it) }
        this.PROCNT?.let { listOfNutrients.add(it) }
        this.TOPCHA?.let { listOfNutrients.add(it) }
        this.CHOLE?.let { listOfNutrients.add(it) }
        this.CHOCDF?.let { listOfNutrients.add(it) }
        this.FAT?.let { listOfNutrients.add(it) }
        this.FIBTG?.let { listOfNutrients.add(it) }
        this.RIBF?.let { listOfNutrients.add(it) }
        this.THIA?.let { listOfNutrients.add(it) }
        this.FAPU?.let { listOfNutrients.add(it) }
        this.NIA?.let { listOfNutrients.add(it) }
        this.VITC?.let { listOfNutrients.add(it) }
        this.FAMS?.let { listOfNutrients.add(it) }
        this.VITB6A?.let { listOfNutrients.add(it) }
        this.WATER?.let { listOfNutrients.add(it) }
        this.VITB12?.let { listOfNutrients.add(it) }
        this.K?.let { listOfNutrients.add(it) }
        this.P?.let { listOfNutrients.add(it) }
        this.NA?.let { listOfNutrients.add(it) }
        this.ZN?.let { listOfNutrients.add(it) }
        this.SUGAR?.let { listOfNutrients.add(it) }
        this.CA?.let { listOfNutrients.add(it) }
        this.MG?.let { listOfNutrients.add(it) }
        this.FE?.let { listOfNutrients.add(it) }
        this.VITK1?.let { listOfNutrients.add(it) }
        this.FOLFD?.let { listOfNutrients.add(it) }
        this.FOLAC?.let { listOfNutrients.add(it) }
        this.FOLDFE?.let { listOfNutrients.add(it) }

        return listOfNutrients.toList()
    }
}