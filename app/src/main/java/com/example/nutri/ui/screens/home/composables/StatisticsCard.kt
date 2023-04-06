package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun StatisticsCard(
    current: MutableState<Int?>,
    norm: Int,
    color: MutableState<androidx.compose.ui.graphics.Color>
){
    Card(
        shape = NutriShape.smallRoundCornerShape,
        modifier = Modifier
            .fillMaxWidth()
            .size(160.dp)
            .padding(24.dp),
        backgroundColor = color.value,
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Calories for today: ")
            Row(horizontalArrangement = Arrangement.Center){
                Text(
                    text = current.value.toString(),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                )
                Text(
                    text = " / $norm",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                )
            }
        }
    }
}

@Preview
@Composable
fun StatisticsPreview(){
    NutriTheme {
        StatisticsCard(
            current = remember{ mutableStateOf(1101)},
            norm = 11010,
            color = remember{ mutableStateOf(com.example.nutri.ui.theme.Tertiary) }
        )
    }
}