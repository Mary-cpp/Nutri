package com.example.nutri.ui.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun NutrientCard(
    name: String,
    info: String,
    amount: String
){
    Surface(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        color =  com.example.nutri.ui.theme.TertiaryVariant,
        shape = NutriShape.smallRoundCornerShape,
        elevation = 2.dp
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(4.dp)
        ){
            Text(text = name, color = Color.White, modifier = Modifier.padding( top=8.dp, bottom = 4.dp, start = 4.dp, end = 4.dp))
            Text(text = info, color = Color.White, modifier = Modifier.padding( top=4.dp, bottom = 8.dp, start = 4.dp, end = 4.dp))
            Text(text = amount, color = Color.White, modifier = Modifier.padding( top=4.dp, bottom = 8.dp, start = 4.dp, end = 4.dp))
        }
    }
}

@Composable
fun NutrientCardTest(){
    Surface(
        modifier = Modifier.padding(4.dp),
        color =  MaterialTheme.colors.surface,
        shape = NutriShape.smallCardCornerShape
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(4.dp)
        ){
            Text(text = "Nutrient name", modifier = Modifier.padding( top=8.dp, bottom = 4.dp, start = 4.dp, end = 4.dp))
            Text(text = "Nutrient info", modifier = Modifier.padding( top=4.dp, bottom = 8.dp, start = 4.dp, end = 4.dp))
            Text(text = "Nutrient amount", modifier = Modifier.padding( top=4.dp, bottom = 8.dp, start = 4.dp, end = 4.dp))
        }
    }
}

@Preview
@Composable
fun NutrientCardPreview(){
    NutriTheme {
        NutrientCardTest()
    }
}