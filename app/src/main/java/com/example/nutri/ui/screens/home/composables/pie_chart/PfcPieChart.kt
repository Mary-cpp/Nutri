package com.example.nutri.ui.screens.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.ui.screens.home.composables.pie_chart.SimpleSliceDrawer
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun PfcPieChart(data: DietPlan){

    var thickness by remember { mutableStateOf(25f) }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PieChart(
            data = data,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(100.dp),
            sliceDrawer = SimpleSliceDrawer(sliceThickness = thickness)
        )
        ChartThickness(thickness = thickness, onValueUpdated = { newValue ->
            thickness = newValue
        })

    }
}

@Composable
fun ChartThickness(
    thickness: Float,
    onValueUpdated: (Float) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Size",
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)
        )
        Slider(
            value = thickness,
            onValueChange = { onValueUpdated(it) },
            valueRange = 10f.rangeTo(100f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PfcPieChartPreview(){
    NutriTheme {
        PfcPieChart(data = DietPlan(kcal = 2100,
            proteins = 58f,
            carbs = 210f,
            fats = 58f
        ))
    }
}