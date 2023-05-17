package com.example.nutri.ui.screens.bmi.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.screens.bmi.BmiViewModel

@Composable
fun TrackUser(
    vm: BmiViewModel
){
    Text(
        text = "Use this value to track your daily meals?",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(8.dp)
            .clickable{ vm.apply { onUsePlanTextClicked() } }
    )
}