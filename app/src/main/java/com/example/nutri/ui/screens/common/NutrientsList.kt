package com.example.nutri.ui.screens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.data.recipe.remote.dto.nutrients.BaseNutrient
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun NutrientsList(nutrients: Map<String, BaseNutrient>){
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
    ){
        nutrients.forEach { (s, baseNutrient) ->
            item(key = s, contentType = baseNutrient){
                NutrientCard(name = s, info = baseNutrient.label, amount = "${baseNutrient.quantity} ${baseNutrient.unit}")
            }
        }
    }
}

@Preview
@Composable
fun NutrientsListPreview(){
    NutriTheme {
        NutrientsList(mapOf())
    }
}