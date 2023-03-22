package com.example.nutri.ui.screens.bmi.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.ui.theme.NutriShape

@Composable
fun DietResultCard(
    plan: DietPlan
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = NutriShape.mediumRoundedCornerShape,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 8.dp
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Daily calories: ${plan.kcal}",
                modifier = Modifier.padding(8.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize)
        }
    }
}

@Preview
@Composable
fun DietCardPreview(){
    DietResultCard(plan = DietPlan(11011))
}