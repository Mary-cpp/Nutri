package com.example.nutri.domain.gateway

import com.example.nutri.data.api.EdamamService
import com.example.nutri.data.recipe.remote.dto.nutrients.BaseNutrient
import com.example.nutri.data.recipe.remote.dto.nutrients.BaseNutrientInstanceCreator
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SerializationTest {

  @Test
  fun convertTotalNutrientFieldsToMap(){
    val nutrientMapType = object : TypeToken<Map<String, BaseNutrient>?>() {}.type

    val gson = GsonBuilder()
      .registerTypeAdapter(
        BaseNutrient::class.java,
        BaseNutrientInstanceCreator()
      ).create()

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

    val actual : Map<String, Map<String, BaseNutrient>> = gson.fromJson(jsonTotalNutrient, nutrientMapType)

    actual.forEach {
      println("${it.key} : ${it.value}")
    }
    //assertEquals(expected, actual)
  }

  @Test
  fun tryToDeserializeResponseProperlyToGetNutrientsAsMap() = runBlocking{

    val gsonBuilder = GsonBuilder()
      .registerTypeAdapter(
        BaseNutrient::class.java,
        BaseNutrientInstanceCreator()
      )

    val apiService = Retrofit.Builder()
      .baseUrl("https://api.edamam.com/api/")
      .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
      .build()
      .create(EdamamService::class.java)

    val recipe = apiService.getNutritionSpecs("c4784311", "ea0d71aa81a3a366d9d7cc58783563ef", "milk 200g")
    println(recipe)
    return@runBlocking
  }

  private val jsonTotalNutrientFields = """{
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
}