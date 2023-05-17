package com.example.nutri.ui.screens.home.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.ui.screens.home.composables.pie_chart.SimpleSliceDrawer
import com.example.nutri.ui.screens.home.composables.pie_chart.Slice
import com.example.nutri.ui.screens.home.composables.pie_chart.SliceDrawer

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
    val colors = listOf(
        com.example.nutri.ui.theme.Secondary,
        com.example.nutri.ui.theme.SurfaceVariant2,
        com.example.nutri.ui.theme.Tertiary)

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
}

@Preview(showBackground = true)
@Composable
fun PieChartPreview() = PieChart(data = DietPlan(2100,58f,58f,210f), modifier = Modifier.fillMaxSize())

private fun calculateChartAngle(
    pieceLength : Float,
    totalLength: Float,
    progress: Float
) : Float = 360.0f * (pieceLength * progress) / totalLength