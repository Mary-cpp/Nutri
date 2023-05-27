package com.example.nutri.ui.screens.home.composables.pie_chart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.domain.bmi.model.DietPlan

@Composable
fun PieChart(
    data: DietPlan,
    modifier: Modifier,
    animation: AnimationSpec<Float> = TweenSpec(durationMillis = 500),
    sliceDrawer: SliceDrawer = SimpleSliceDrawer()
) {
    val chartData = listOf(data.proteins, data.fats, data.carbs)
    val transitionProgress = remember(chartData) { Animatable(initialValue = 0f) }

    LaunchedEffect(data){
        transitionProgress.animateTo(1f, animationSpec = animation)
    }

    PieChartContent(data = data,
        modifier = modifier,
        progress = transitionProgress.value, sliceDrawer = sliceDrawer)
}

@Composable
fun PieChartContent(
    data: DietPlan,
    modifier: Modifier,
    progress: Float,
    sliceDrawer: SliceDrawer
){
    val pfc = listOf(data.proteins, data.fats, data.carbs)

    val fatsColor = com.example.nutri.ui.theme.SurfaceVariant2
    val proteinsColor = com.example.nutri.ui.theme.Secondary
    val carbsColor = com.example.nutri.ui.theme.Tertiary
    val colors = listOf(fatsColor, proteinsColor, carbsColor)

    val chartData = pfc.zip(colors)

    Canvas(modifier = modifier){
        drawIntoCanvas {
            var startArc = 0f
            val totalAmount = data.carbs+data.proteins+data.fats

            chartData.forEach { value->
                val arc = calculateChartAngle(
                    pieceLength = value.first,
                    totalLength = totalAmount,
                    progress = progress
                )

                sliceDrawer.draw(
                    scope = this,
                    canvas = drawContext.canvas,
                    size = size,
                    startAngle = startArc,
                    sweepAngle = arc,
                    slice = Slice(value = value.first, color = value.second)
                )
                startArc += arc
            }
        }
    }
    ChartInfo(proteinsColor, fatsColor, carbsColor)
}

@Composable
fun ChartInfo(
    proteinsColor: Color,
    fatsColor: Color,
    carbsColor: Color,
    ){
    val proteinsString = LocalContext.current.resources.getString(com.example.nutri.R.string.proteins)
    val fatsString = LocalContext.current.resources.getString(com.example.nutri.R.string.fats)
    val carbsString = LocalContext.current.resources.getString(com.example.nutri.R.string.carbs)

    Row(
        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(modifier = Modifier
            .wrapContentSize()
            .padding(end = 16.dp)){
            drawCircle(color = proteinsColor, radius = 20.dp.value)
        }
        Text(modifier = Modifier.padding(end = 16.dp),text = proteinsString)
        Canvas(modifier = Modifier
            .wrapContentSize()
            .padding(end = 16.dp)){
            drawCircle(color = fatsColor, radius = 20.dp.value)
        }
        Text(text = fatsString, modifier = Modifier.padding(end = 16.dp))
        Canvas(modifier = Modifier
            .wrapContentSize()
            .padding(end = 16.dp)){
            drawCircle(color = carbsColor, radius = 20.dp.value)
        }
        Text(text = carbsString)
    }

}

@Preview(showBackground = true)
@Composable
fun PieChartPreview() = PieChart(data = DietPlan(2100,58f,58f,210f), modifier = Modifier.fillMaxSize())

private fun calculateChartAngle(
    pieceLength : Float,
    totalLength: Float,
    progress: Float
) : Float = 360.0f * (pieceLength * progress) / totalLength