package com.example.nutri.ui.screens.bmi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.bmi.model.ActivityType
import com.example.nutri.ui.theme.NutriShape

@Composable
fun ActivityDropDownListButton(
    activity: MutableState<ActivityType>,
    mutableString: MutableState<String>,
    menuItems: List<String>,
    buttonSize: Int,
    shape: RoundedCornerShape = NutriShape.mediumRoundedCornerShape,
    color: Color
){
    val isExpanded = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.padding(top = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { isExpanded.value = true },
            modifier = Modifier
                .padding(start = 8.dp)
                .size(height = 48.dp, width = buttonSize.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            shape = shape
        ) {
            Text(
                text = mutableString.value,
                fontSize = MaterialTheme.typography.caption.fontSize
            )
        }
        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }) {
            menuItems.forEach{
                DropdownMenuItem(onClick =
                {
                    mutableString.value = it
                    activity.value = ActivityType.valueOf(it)
                }) {
                    Text(text = it)
                }
            }
        }
    }
}