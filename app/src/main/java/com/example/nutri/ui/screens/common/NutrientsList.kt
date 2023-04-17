package com.example.nutri.ui.screens.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.data.recipe.remote.dto.nutrients.BaseNutrient
import com.example.nutri.ui.theme.NutriTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NutrientsList(nutrients: Map<String, BaseNutrient>){

    Column{
        Text(
            text = "Nutrients:",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.body1.fontSize
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
        ){
            nutrients.forEach { (s, baseNutrient) ->
                item(key = s, contentType = baseNutrient){
                    NutrientCard(name = s, info = baseNutrient.label, amount = "${baseNutrient.quantity} ${baseNutrient.unit}")
                }
            }
        }
    }
}

@Preview
@Composable
fun NutrientsListPreview(){
    NutriTheme {
        NutrientsList(mapOf("Nutrient" to BaseNutrient().setFields(label = "Nutrient INFO", quantity = 20.0, unit = "mg")))
    }
}