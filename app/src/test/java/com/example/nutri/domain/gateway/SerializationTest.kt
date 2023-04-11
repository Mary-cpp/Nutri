package com.example.nutri.domain.gateway

import com.example.nutri.data.recipe.remote.dto.Characteristics
import com.example.nutri.data.recipe.remote.dto.Ingredient
import com.example.nutri.data.recipe.remote.dto.TotalNutrients
import com.example.nutri.data.recipe.remote.dto.nutrients.*
import com.example.nutri.data.recipe.remote.repository.ApiGatewayImpl
import com.example.nutri.domain.recipes.model.Recipe
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SerializationTest {

    //private var recipe: Recipe
    val apiGatewayImpl: ApiGatewayImpl = ApiGatewayImpl()
    val gson = Gson()

    val jsonStringENERC_KCAL = """{"label":"Energy","quantity":122.0,"unit":"kcal"}"""
    val jsonMapENERC_KCAL = """{"ENERC_KCAL":{"label":"Energy","quantity":122.0,"unit":"kcal"}}"""

    @Test
    fun recipeSerializationCheck() = runBlocking {
        val param = "milk 200g"
        val actual = apiGatewayImpl.receiveRecipeData(param)
        val expected = makeRecipe(actual.uri)

        assertEquals("SERIALIZATION ERROR. " +
                "Serialized object does not match template or API has changed",
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
      println(actual)
    assertEquals(expected, actual)
  }

  @Test
  fun convertTotalNutrientFieldsToMap(){
    val nutrientMapType = object : TypeToken<Map<String, BaseNutrient>?>() {}.type

    val gson = GsonBuilder()
      .registerTypeAdapter(
        BaseNutrient::class.java,
        BaseNutrientInstanceCreator()
      ).create()

    val expected = makeNutrients()
    val actual : Map<String, BaseNutrient> = gson.fromJson(jsonTotalNutrientFields, nutrientMapType)

    actual.forEach {
      println("${it.key} : ${it.value}")
    }
    //assertEquals(expected, actual)
  }

  @Test
  fun convertTotalNutrientToMap(){
    val nutrientMapType = object : TypeToken<Map<String, Map<String, BaseNutrient>>?>() {}.type

    val gson = GsonBuilder()
      .registerTypeAdapter(
        BaseNutrient::class.java,
        BaseNutrientInstanceCreator()
      ).create()

    val expected = makeNutrients()
    val actual : Map<String, Map<String, BaseNutrient>> = gson.fromJson(jsonTotalNutrient, nutrientMapType)

    actual.forEach {
      println("${it.key} : ${it.value}")
    }
    //assertEquals(expected, actual)
  }

  @Test
  fun convertToJsonCheck(){

    val expected = jsonStringENERC_KCAL
    val actual = gson.toJson(makeEnerKcal())

    assertEquals(
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

    fun makeRecipe(uri: String?)
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
      VITD = VITD( "Vitamin D (D2 + D3)",2.6,"µg"),
      ENERCKCAL = ENERC_KCAL("Energy",122.0,"kcal"),
      FAT = FAT("Total lipid (fat)", 6.5, "g"),
      FASAT = FASAT("Fatty acids, total saturated", 3.73, "g"),
      VITARAE = VITA_RAE("Vitamin A, RAE", 92.0,"µg"),
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
      VITB12 = VITB12("Vitamin B-12", 0.9,"µg"),
      WATER = WATER("Water", 176.26, "g"),
      K = K("Potassium, K",264.0,"mg"),
      P = P("Phosphorus, P", 168.0,"mg"),
      NA = NA( "Sodium, Na", 86.0,"mg"),
      ZN = ZN("Zinc, Zn",0.74, "mg"),
      SUGAR = SUGAR("Sugars, total", 10.1,"g"),
      CA = CA("Calcium, Ca",226.0,"mg"),
      MG = MG( "Magnesium, Mg",20.0,"mg"),
      FE = FE("Iron, Fe", 0.06,"mg"),
      VITK1 = VITK1("Vitamin K (phylloquinone)", 0.6,"µg"),
      FOLFD = FOLFD("Folate, food", 10.0,"µg"),
      FOLAC = FOLAC("Folic acid", 0.0, "µg"),
      FOLDFE = FOLDFE( "Folate, DFE", 10.0, "µg")
      ),
      totalDaily = TotalNutrients(
      VITD = VITD( "Vitamin D", 17.333333333333332,"%"),
      ENERCKCAL = ENERC_KCAL("Energy",6.1,"%"),
      FAT = FAT("Fat", 10.0, "%"),
      FASAT = FASAT("Saturated", 18.65, "%"),
      VITARAE = VITA_RAE("Vitamin A", 10.222222222222221,"%"),
      PROCNT = PROCNT("Protein", 12.6, "%"),
      TOPCHA = TOCPHA("Vitamin E",  0.9333333333333335,"%"),
      CHOLE = CHOLE("Cholesterol",6.666666666666667,"%"),
      CHOCDF = CHOCDF( "Carbs", 3.2, "%"),
      FIBTG = FIBTG("Fiber",0.0,"%"),
      RIBF = RIBF("Riboflavin (B2)", 26.000000000000004,"%"),
      THIA = THIA( "Thiamin (B1)",7.666666666666666,"%"),
      FAPU = null,
      NIA = NIA("Niacin (B3)", 1.1125,"%"),
      VITC = VITC("Vitamin C", 0.0,"%"),
      FAMS = null,
      VITB6A = VITB6A("Vitamin B6", 5.538461538461537, "%"),
      VITB12 = VITB12("Vitamin B12", 37.5,"%"),
      WATER = null,
      K = K("Potassium",5.617021276595745,"%"),
      P = P("Phosphorus", 24.0,"%"),
      NA = NA( "Sodium", 3.5833333333333335,"%"),
      ZN = ZN("Zinc",6.7272727272727275, "%"),
      SUGAR = null,
      CA = CA("Calcium",22.6,"%"),
      MG = MG( "Magnesium",4.761904761904762,"%"),
      FE = FE("Iron", 0.3333333333333333,"%"),
      VITK1 = VITK1("Vitamin K", 0.5,"%"),
      FOLFD = null,
      FOLAC = null,
      FOLDFE = FOLDFE( "Folate equivalent (total)", 2.5, "%")
      ),
      ingredients = listOf(
        Ingredient(
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
      VITD = VITD( "Vitamin D",102.0,"IU"),
      ENERCKCAL = ENERC_KCAL("Energy",122.0,"kcal"),
      FAT = FAT("Total lipid (fat)", 6.5, "g"),
      FASAT = FASAT("Fatty acids, total saturated", 3.73, "g"),
      VITARAE = VITA_RAE("Vitamin A, RAE", 92.0,"µg"),
      PROCNT = PROCNT("Protein", 6.3, "g"),
      TOPCHA = TOCPHA("Vitamin E (alpha-tocopherol)", 0.14,"mg"),
      CHOLE = CHOLE("Cholesterol",20.0,"mg"),
      CHOCDF = CHOCDF( "Carbohydrate, by difference", 9.6, "g"),
      FIBTG = FIBTG("Fiber, total dietary",0.0,"g"),
      RIBF = RIBF("Riboflavin", 0.338,"mg"),
      THIA = THIA( "Thiamin",0.092,"mg"),
      FAPU = FAPU( "Fatty acids, total polyunsaturated",0.39,"g"),
      NIA = NIA("Niacin", 0.17800000000000002,"mg"),
      VITC = VITC("Vitamin C, total ascorbic acid", 0.0,"mg"),
      FAMS = FAMS( "Fatty acids, total monounsaturated", 1.624,"g"),
      VITB6A = VITB6A("Vitamin B-6", 0.072, "mg"),
      VITB12 = VITB12("Vitamin B-12", 0.9,"µg"),
      WATER = WATER("Water", 176.26, "g"),
      K = K("Potassium, K",264.0,"mg"),
      P = P("Phosphorus, P", 168.0,"mg"),
      NA = NA( "Sodium, Na", 86.0,"mg"),
      ZN = ZN("Zinc, Zn",0.74, "mg"),
      SUGAR = SUGAR("Sugars, total", 10.1,"g"),
      CA = CA("Calcium, Ca",226.0,"mg"),
      MG = MG( "Magnesium, Mg",20.0,"mg"),
      FE = FE("Iron, Fe", 0.06,"mg"),
      VITK1 = VITK1("Vitamin K (phylloquinone)", 0.6,"µg"),
      FOLFD = FOLFD("Folate, food", 10.0,"µg"),
      FOLAC = FOLAC("Folic acid", 0.0, "µg"),
      FOLDFE = FOLDFE( "Folate, DFE", 10.0, "µg")
      ),
      measureUri = null,
      status = "OK"
      )))
      ),
      totalNutrientsKCal = TotalNutrientsKCal(
      ENERCKCAL = ENERC_KCAL("Energy",121.0,"kcal"),
      PROCNTKCAL = PROCNT_KCAL("Calories from protein", 25.0, "kcal"),
      FATKCAL = FAT_KCAL("Calories from fat", 58.0, "kcal"),
      CHOCDFKCAL = CHOCDF_KCAL("Calories from carbohydrates", 38.0, "kcal")
      )
    )

  val jsonTotalNutrientFields = """{
        "ENERC_KCAL": {
            "label": "Energy",
            "quantity": 122.0,
            "unit": "kcal"
        },
        "FAT": {
            "label": "Total lipid (fat)",
            "quantity": 6.5,
            "unit": "g"
        },
        "FASAT": {
            "label": "Fatty acids, total saturated",
            "quantity": 3.73,
            "unit": "g"
        },
        "FAMS": {
            "label": "Fatty acids, total monounsaturated",
            "quantity": 1.624,
            "unit": "g"
        },
        "FAPU": {
            "label": "Fatty acids, total polyunsaturated",
            "quantity": 0.39,
            "unit": "g"
        },
        "CHOCDF": {
            "label": "Carbohydrate, by difference",
            "quantity": 9.6,
            "unit": "g"
        },
        "CHOCDF.net": {
            "label": "Carbohydrates (net)",
            "quantity": 9.6,
            "unit": "g"
        },
        "FIBTG": {
            "label": "Fiber, total dietary",
            "quantity": 0.0,
            "unit": "g"
        },
        "SUGAR": {
            "label": "Sugars, total",
            "quantity": 10.1,
            "unit": "g"
        },
        "PROCNT": {
            "label": "Protein",
            "quantity": 6.3,
            "unit": "g"
        },
        "CHOLE": {
            "label": "Cholesterol",
            "quantity": 20.0,
            "unit": "mg"
        },
        "NA": {
            "label": "Sodium, Na",
            "quantity": 86.0,
            "unit": "mg"
        },
        "CA": {
            "label": "Calcium, Ca",
            "quantity": 226.0,
            "unit": "mg"
        },
        "MG": {
            "label": "Magnesium, Mg",
            "quantity": 20.0,
            "unit": "mg"
        },
        "K": {
            "label": "Potassium, K",
            "quantity": 264.0,
            "unit": "mg"
        },
        "FE": {
            "label": "Iron, Fe",
            "quantity": 0.06,
            "unit": "mg"
        },
        "ZN": {
            "label": "Zinc, Zn",
            "quantity": 0.74,
            "unit": "mg"
        },
        "P": {
            "label": "Phosphorus, P",
            "quantity": 168.0,
            "unit": "mg"
        },
        "VITA_RAE": {
            "label": "Vitamin A, RAE",
            "quantity": 92.0,
            "unit": "µg"
        },
        "VITC": {
            "label": "Vitamin C, total ascorbic acid",
            "quantity": 0.0,
            "unit": "mg"
        },
        "THIA": {
            "label": "Thiamin",
            "quantity": 0.092,
            "unit": "mg"
        },
        "RIBF": {
            "label": "Riboflavin",
            "quantity": 0.338,
            "unit": "mg"
        },
        "NIA": {
            "label": "Niacin",
            "quantity": 0.178,
            "unit": "mg"
        },
        "VITB6A": {
            "label": "Vitamin B-6",
            "quantity": 0.072,
            "unit": "mg"
        },
        "FOLDFE": {
            "label": "Folate, DFE",
            "quantity": 10.0,
            "unit": "µg"
        },
        "FOLFD": {
            "label": "Folate, food",
            "quantity": 10.0,
            "unit": "µg"
        },
        "FOLAC": {
            "label": "Folic acid",
            "quantity": 0.0,
            "unit": "µg"
        },
        "VITB12": {
            "label": "Vitamin B-12",
            "quantity": 0.9,
            "unit": "µg"
        },
        "VITD": {
            "label": "Vitamin D (D2 + D3)",
            "quantity": 2.6,
            "unit": "µg"
        },
        "TOCPHA": {
            "label": "Vitamin E (alpha-tocopherol)",
            "quantity": 0.14,
            "unit": "mg"
        },
        "VITK1": {
            "label": "Vitamin K (phylloquinone)",
            "quantity": 0.6,
            "unit": "µg"
        },
        "WATER": {
            "label": "Water",
            "quantity": 176.26,
            "unit": "g"
        }
    }"""

  val jsonTotalNutrient = """"{totalNutrients":{
        "ENERC_KCAL": {
            "label": "Energy",
            "quantity": 122.0,
            "unit": "kcal"
        },
        "FAT": {
            "label": "Total lipid (fat)",
            "quantity": 6.5,
            "unit": "g"
        },
        "FASAT": {
            "label": "Fatty acids, total saturated",
            "quantity": 3.73,
            "unit": "g"
        },
        "FAMS": {
            "label": "Fatty acids, total monounsaturated",
            "quantity": 1.624,
            "unit": "g"
        },
        "FAPU": {
            "label": "Fatty acids, total polyunsaturated",
            "quantity": 0.39,
            "unit": "g"
        },
        "CHOCDF": {
            "label": "Carbohydrate, by difference",
            "quantity": 9.6,
            "unit": "g"
        },
        "CHOCDF.net": {
            "label": "Carbohydrates (net)",
            "quantity": 9.6,
            "unit": "g"
        },
        "FIBTG": {
            "label": "Fiber, total dietary",
            "quantity": 0.0,
            "unit": "g"
        },
        "SUGAR": {
            "label": "Sugars, total",
            "quantity": 10.1,
            "unit": "g"
        },
        "PROCNT": {
            "label": "Protein",
            "quantity": 6.3,
            "unit": "g"
        },
        "CHOLE": {
            "label": "Cholesterol",
            "quantity": 20.0,
            "unit": "mg"
        },
        "NA": {
            "label": "Sodium, Na",
            "quantity": 86.0,
            "unit": "mg"
        },
        "CA": {
            "label": "Calcium, Ca",
            "quantity": 226.0,
            "unit": "mg"
        },
        "MG": {
            "label": "Magnesium, Mg",
            "quantity": 20.0,
            "unit": "mg"
        },
        "K": {
            "label": "Potassium, K",
            "quantity": 264.0,
            "unit": "mg"
        },
        "FE": {
            "label": "Iron, Fe",
            "quantity": 0.06,
            "unit": "mg"
        },
        "ZN": {
            "label": "Zinc, Zn",
            "quantity": 0.74,
            "unit": "mg"
        },
        "P": {
            "label": "Phosphorus, P",
            "quantity": 168.0,
            "unit": "mg"
        },
        "VITA_RAE": {
            "label": "Vitamin A, RAE",
            "quantity": 92.0,
            "unit": "µg"
        },
        "VITC": {
            "label": "Vitamin C, total ascorbic acid",
            "quantity": 0.0,
            "unit": "mg"
        },
        "THIA": {
            "label": "Thiamin",
            "quantity": 0.092,
            "unit": "mg"
        },
        "RIBF": {
            "label": "Riboflavin",
            "quantity": 0.338,
            "unit": "mg"
        },
        "NIA": {
            "label": "Niacin",
            "quantity": 0.178,
            "unit": "mg"
        },
        "VITB6A": {
            "label": "Vitamin B-6",
            "quantity": 0.072,
            "unit": "mg"
        },
        "FOLDFE": {
            "label": "Folate, DFE",
            "quantity": 10.0,
            "unit": "µg"
        },
        "FOLFD": {
            "label": "Folate, food",
            "quantity": 10.0,
            "unit": "µg"
        },
        "FOLAC": {
            "label": "Folic acid",
            "quantity": 0.0,
            "unit": "µg"
        },
        "VITB12": {
            "label": "Vitamin B-12",
            "quantity": 0.9,
            "unit": "µg"
        },
        "VITD": {
            "label": "Vitamin D (D2 + D3)",
            "quantity": 2.6,
            "unit": "µg"
        },
        "TOCPHA": {
            "label": "Vitamin E (alpha-tocopherol)",
            "quantity": 0.14,
            "unit": "mg"
        },
        "VITK1": {
            "label": "Vitamin K (phylloquinone)",
            "quantity": 0.6,
            "unit": "µg"
        },
        "WATER": {
            "label": "Water",
            "quantity": 176.26,
            "unit": "g"
        }
    }}"""

  fun makeNutrients() = TotalNutrients(
    VITD = VITD( "Vitamin D (D2 + D3)",2.6,"µg"),
    ENERCKCAL = ENERC_KCAL("Energy",122.0,"kcal"),
    FAT = FAT("Total lipid (fat)", 6.5, "g"),
    FASAT = FASAT("Fatty acids, total saturated", 3.73, "g"),
    VITARAE = VITA_RAE("Vitamin A, RAE", 92.0,"µg"),
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
    VITB12 = VITB12("Vitamin B-12", 0.9,"µg"),
    WATER = WATER("Water", 176.26, "g"),
    K = K("Potassium, K",264.0,"mg"),
    P = P("Phosphorus, P", 168.0,"mg"),
    NA = NA( "Sodium, Na", 86.0,"mg"),
    ZN = ZN("Zinc, Zn",0.74, "mg"),
    SUGAR = SUGAR("Sugars, total", 10.1,"g"),
    CA = CA("Calcium, Ca",226.0,"mg"),
    MG = MG( "Magnesium, Mg",20.0,"mg"),
    FE = FE("Iron, Fe", 0.06,"mg"),
    VITK1 = VITK1("Vitamin K (phylloquinone)", 0.6,"µg"),
    FOLFD = FOLFD("Folate, food", 10.0,"µg"),
    FOLAC = FOLAC("Folic acid", 0.0, "µg"),
    FOLDFE = FOLDFE( "Folate, DFE", 10.0, "µg")
  )
}