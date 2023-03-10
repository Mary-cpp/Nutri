package com.example.nutri.domain.gateway

import com.example.nutri.data.dto.Characteristics
import com.example.nutri.data.dto.Ingredient
import com.example.nutri.data.dto.TotalNutrients
import com.example.nutri.data.dto.nutrients.*
import com.example.nutri.domain.model.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class ApiGatewayImplTest {

    //private var recipe: Recipe
    val apiGatewayImpl: ApiGatewayImpl= ApiGatewayImpl()
    val gson = Gson()

    val jsonStringENERC_KCAL = """{"label":"Energy","quantity":122.0,"unit":"kcal"}"""
    val jsonMapENERC_KCAL = """{"ENERC_KCAL":{"label":"Energy","quantity":122.0,"unit":"kcal"}}"""

    @Ignore
    @Test
    fun receiveRecipeData() = runBlocking {
        val param = "milk 200g"
        val actual = apiGatewayImpl.receiveRecipeData(param)
        val expected = makeRecipe(actual.uri)

        assertEquals("SERIALIZATION ERROR. Serialized object does not match template or API has changed",
            expected, actual)
    }

  @Test
  fun convertToJsonMapCheck(){
      val expected = jsonMapENERC_KCAL
      val actual = gson.toJson(makeEnerKcalMap())

      assertEquals(expected, actual)
  }

  @Test
  fun convertFromJsonMapCheck(){

    val empMapType = object : TypeToken<Map<String?, ENERC_KCAL?>?>() {}.type
      val expected = makeEnerKcalMap()
      val actual : Map<String?, ENERC_KCAL?> = gson.fromJson(jsonMapENERC_KCAL, empMapType)

    assertEquals(expected, actual)
  }

  @Test
  fun convertToJsonCheck(){

    val expected = jsonStringENERC_KCAL
    val actual = gson.toJson(makeEnerKcal())

    assertEquals("CAN NOT CONVERT DATA CLASS TO JSON PROPERLY",
      expected,
      actual)
  }

  @Test
  fun convertFromJsonCheck() {

    val expected = makeEnerKcal()
    val actual = gson.fromJson(jsonStringENERC_KCAL, ENERC_KCAL::class.java)

    assertEquals("CAN NOT CONVERT JSON PROPERLY",
      expected,
      actual)
  }

    fun makeEnerKcalMap(): Map<String, ENERC_KCAL>{
      return mapOf("ENERC_KCAL" to makeEnerKcal())
    }

    fun makeEnerKcal()
    = ENERC_KCAL(
      label = "Energy",
      quantity = 122.0,
      "kcal"
    )

    fun  makeRecipe(uri: String?)
    = Recipe(
      uri = uri,
      calories = 122,
      totalWeight = 200.0,
      dietLabels = listOf("LOW_SODIUM"),
      healthLabels = listOf(
        "LOW_POTASSIUM",
        "KIDNEY_FRIENDLY",
        "KETO_FRIENDLY",
        "VEGETARIAN",
        "PESCATARIAN",
        "MEDITERRANEAN",
        "GLUTEN_FREE",
        "WHEAT_FREE",
        "EGG_FREE",
        "PEANUT_FREE",
        "TREE_NUT_FREE",
        "SOY_FREE",
        "FISH_FREE",
        "SHELLFISH_FREE",
        "PORK_FREE",
        "RED_MEAT_FREE",
        "CRUSTACEAN_FREE",
        "CELERY_FREE",
        "MUSTARD_FREE",
        "SESAME_FREE",
        "LUPINE_FREE",
        "MOLLUSK_FREE",
        "ALCOHOL_FREE",
        "NO_OIL_ADDED",
        "NO_SUGAR_ADDED",
        "SULPHITE_FREE",
        "KOSHER"),
      cautions = emptyList(),
      totalNutrients = TotalNutrients(
      VITD = VITD( "Vitamin D (D2 + D3)",2.6,"??g"),
      ENERCKCAL = ENERC_KCAL("Energy",122.0,"kcal"),
      FAT = FAT("Total lipid (fat)", 6.5, "g"),
      FASAT = FASAT("Fatty acids, total saturated", 3.73, "g"),
      VITARAE = VITA_RAE("Vitamin A, RAE", 92.0,"??g"),
      PROCNT = PROCNT("Protein", 6.3, "g"),
      TOPCHA = TOCPHA("Vitamin E (alpha-tocopherol)", 0.14,"mg"),
      CHOLE = CHOLE("Cholesterol",20.0,"mg"),
      CHOCDF = CHOCDF( "Carbohydrate, by difference", 9.6, "g"),
      FIBTG = FIBTG("Fiber, total dietary",0.0,"g"),
      RIBF = RIBF("Riboflavin", 0.338,"mg"),
      THIA = THIA( "Thiamin",0.092,"mg"),
      FAPU = FAPU( "Fatty acids, total polyunsaturated",0.39,"g"),
      NIA = NIA("Niacin", 0.178,"mg"),
      VITC = VITC("Vitamin C, total ascorbic acid", 0.0,"mg"),
      FAMS = FAMS( "Fatty acids, total monounsaturated", 1.624,"g"),
      VITB6A = VITB6A("Vitamin B-6", 0.072, "mg"),
      VITB12 = VITB12("Vitamin B-12", 0.9,"??g"),
      WATER = WATER("Water", 176.26, "g"),
      K = K("Potassium, K",264.0,"mg"),
      P = P("Phosphorus, P", 168.0,"mg"),
      NA = NA( "Sodium, Na", 86.0,"mg"),
      ZN = ZN("Zinc, Zn",0.74, "mg"),
      SUGAR = SUGAR("Zinc, Zn", 0.74,"mg"),
      CA = CA("Calcium, Ca",226.0,"mg"),
      MG = MG( "Magnesium, Mg",20.0,"mg"),
      FE = FE("Iron, Fe", 0.06,"mg"),
      VITK1 = VITK1("Vitamin K (phylloquinone)", 0.6,"??g"),
      FOLFD = FOLFD("Folate, food", 10.0,"??g"),
      FOLAC = FOLAC("Folic acid", 0.0, "??g"),
      FOLDFE = FOLDFE( "Folate, DFE", 10.0, "??g")),
      totalDaily = TotalNutrients(
      VITD = VITD( "Vitamin D", 17.333333333333332,"%"),
      ENERCKCAL = ENERC_KCAL("Energy",6.1,"%"),
      FAT = FAT("Total lipid (fat)", 10.0, "%"),
      FASAT = FASAT("Saturated", 18.65, "%"),
      VITARAE = VITA_RAE("Vitamin A", 10.222222222222221,"%"),
      PROCNT = PROCNT("Protein", 12.6, "%"),
      TOPCHA = TOCPHA("Vitamin E",  0.9333333333333335,"%"),
      CHOLE = CHOLE("Cholesterol",6.666666666666667,"%"),
      CHOCDF = CHOCDF( "Carbs", 3.2, "%"),
      FIBTG = FIBTG("Fiber",0.0,"%"),
      RIBF = RIBF("Riboflavin (B2)", 26.000000000000004,"%"),
      THIA = THIA( "Thiamin (B1)",7.666666666666666,"%"),
      FAPU = FAPU( "Fatty acids, total polyunsaturated",0.39,"%"),
      NIA = NIA("Niacin (B3)", 1.1125,"%"),
      VITC = VITC("Vitamin C", 0.0,"%"),
      FAMS = FAMS( "Fatty acids, total monounsaturated", 1.624,"%"),
      VITB6A = VITB6A("Vitamin B-6", 5.538461538461537, "%"),
      VITB12 = VITB12("Vitamin B12", 37.5,"%"),
      WATER = WATER("Water", 176.26, "%"),
      K = K("Potassium",5.617021276595745,"%"),
      P = P("Phosphorus, P", 24.0,"%"),
      NA = NA( "Sodium", 3.5833333333333335,"%"),
      ZN = ZN("Zinc",6.7272727272727275, "%"),
      SUGAR = SUGAR("Zinc, Zn", 0.74,"%"),
      CA = CA("Calcium",22.6,"%"),
      MG = MG( "Magnesium",4.761904761904762,"%"),
      FE = FE("Iron", 0.3333333333333333,"%"),
      VITK1 = VITK1("Vitamin K", 0.5,"%"),
      FOLFD = FOLFD("Folate, food", 10.0,"%"),
      FOLAC = FOLAC("Folic acid", 0.0, "%"),
      FOLDFE = FOLDFE( "Folate equivalent (total)", 2.5, "%")
      ),
      ingredients = listOf(Ingredient(
      text = "milk 200g",
      parsed = listOf(Characteristics(
      quantity = 200.0,
      measure = "gram",
      foodMatch = "milk",
      food = "whole milk",
      foodId = "food_b49rs1kaw0jktabzkg2vvanvvsis",
      weight = 200.0,
      retainedWeight = 200.0,
      nutrients = TotalNutrients(
      VITD = VITD( "Vitamin D (D2 + D3)",102.0,"IU"),
      ENERCKCAL = ENERC_KCAL("Energy",122.0,"kcal"),
      FAT = FAT("Total lipid (fat)", 6.5, "g"),
      FASAT = FASAT("Fatty acids, total saturated", 3.73, "g"),
      VITARAE = VITA_RAE("Vitamin A, RAE", 92.0,"??g"),
      PROCNT = PROCNT("Protein", 6.3, "g"),
      TOPCHA = TOCPHA("Vitamin E (alpha-tocopherol)", 0.14,"mg"),
      CHOLE = CHOLE("Cholesterol",20.0,"mg"),
      CHOCDF = CHOCDF( "Carbohydrate, by difference", 9.6, "g"),
      FIBTG = FIBTG("Fiber, total dietary",0.0,"g"),
      RIBF = RIBF("Riboflavin", 0.338,"mg"),
      THIA = THIA( "Thiamin",0.092,"mg"),
      FAPU = FAPU( "Fatty acids, total polyunsaturated",0.39,"g"),
      NIA = NIA("Niacin", 0.178,"mg"),
      VITC = VITC("Vitamin C, total ascorbic acid", 0.0,"mg"),
      FAMS = FAMS( "Fatty acids, total monounsaturated", 1.624,"g"),
      VITB6A = VITB6A("Vitamin B-6", 0.072, "mg"),
      VITB12 = VITB12("Vitamin B-12", 0.9,"??g"),
      WATER = WATER("Water", 176.26, "g"),
      K = K("Potassium, K",264.0,"mg"),
      P = P("Phosphorus, P", 168.0,"mg"),
      NA = NA( "Sodium, Na", 86.0,"mg"),
      ZN = ZN("Zinc, Zn",0.74, "mg"),
      SUGAR = SUGAR("Zinc, Zn", 0.74,"mg"),
      CA = CA("Calcium, Ca",226.0,"mg"),
      MG = MG( "Magnesium, Mg",20.0,"mg"),
      FE = FE("Iron, Fe", 0.06,"mg"),
      VITK1 = VITK1("Vitamin K (phylloquinone)", 0.6,"??g"),
      FOLFD = FOLFD("Folate, food", 10.0,"??g"),
      FOLAC = FOLAC("Folic acid", 0.0, "??g"),
      FOLDFE = FOLDFE( "Folate, DFE", 10.0, "??g")),
      measureUri = "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
      status = "OK"
      )))),
      totalNutrientsKCal = TotalNutrientsKCal(
      ENERCKCAL = ENERC_KCAL("Energy",121.0,"kcal"),
      PROCNTKCAL = PROCNT_KCAL("Calories from protein", 25.0, "kcal"),
      FATKCAL = FAT_KCAL("Calories from fat", 58.0, "kcal"),
      CHOCDFKCAL = CHOCDF_KCAL("Calories from carbohydrates", 38.0, "kcal")
      )
    )
}