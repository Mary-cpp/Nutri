package com.example.nutri.ui.screens.home.composables

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.domain.statistics.model.Water
import com.example.nutri.ui.theme.NutriTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WaterInfoCard(
    onRemove: () -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier,
    water: MutableState<Water>,
){
    val isFullWaterVisible by remember { derivedStateOf { water.value.amount > 0 }}

    Surface(
        modifier = modifier.padding(4.dp).wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        color = MaterialTheme.colors.background,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(water.value.amount){
                AnimatedVisibility(
                    visible = isFullWaterVisible,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f))
                            + fadeIn() + expandVertically(expandFrom = Alignment.CenterVertically),
                    exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = ImageVector
                            .vectorResource(R.drawable.water_full48px),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "LowWaterIcon",
                        modifier = Modifier.clickable { onRemove.invoke() }
                    )
                }
            }
            Icon(
                imageVector = ImageVector
                    .vectorResource(R.drawable.water_low_48px),
                tint = MaterialTheme.colors.primary,
                contentDescription = "LowWaterIcon",
                modifier = Modifier.clickable { onAdd.invoke() }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WaterInfoCardTest(
    modifier: Modifier = Modifier
){
    var waterAmount by remember { mutableStateOf(3) }
    val isFullWaterVisible by remember { derivedStateOf { waterAmount > 0 }}

    Surface(
        modifier = modifier.padding(4.dp).wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        color = MaterialTheme.colors.background,
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(waterAmount){
                AnimatedVisibility(
                    visible = isFullWaterVisible,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f))
                            + fadeIn() + expandVertically(expandFrom = Alignment.CenterVertically),
                    exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = ImageVector
                            .vectorResource(R.drawable.water_full48px),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "LowWaterIcon",
                        modifier = modifier.clickable { waterAmount-- }
                    )
                }
            }
            Icon(
                imageVector = ImageVector
                    .vectorResource(R.drawable.water_low_48px),
                tint = MaterialTheme.colors.primary,
                contentDescription = "LowWaterIcon",
                modifier = modifier.clickable { waterAmount++ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WaterCardPreview(){
    NutriTheme {
        WaterInfoCardTest()
    }
}