package com.example.nutri.ui.screens.bmi.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.ui.theme.NutriShape
import kotlin.math.roundToInt

@Composable
fun DietResultCard(
    plan: DietPlan,
    onSaveButtonClicked: () -> Unit
) {

    val calories = LocalContext.current.resources.getString(R.string.calories) + " " + plan.kcal
    val proteins = LocalContext.current.resources.getString(R.string.proteins) + ": " + plan.proteins.roundToInt()
    val fats = LocalContext.current.resources.getString(R.string.fats) + ": " + plan.fats.roundToInt()
    val carbs = LocalContext.current.resources.getString(R.string.carbs) + ": " + plan.carbs.roundToInt()


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp, bottom = 16.dp),
        shape = NutriShape.mediumRoundedCornerShape,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 8.dp
    ){
        Column(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                IconButton(onClick = { onSaveButtonClicked.invoke() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.save48px),
                        contentDescription = "SaveUserPlan",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
                Text(
                    text = calories,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize)
            }

            Text(
                text = proteins,
                modifier = Modifier.padding(8.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )

            Text(
                text = fats,
                modifier = Modifier.padding(8.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )

            Text(
                text = carbs,
                modifier = Modifier.padding(8.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }
}

@Preview
@Composable
fun DietCardPreview(){
    DietResultCard(plan = DietPlan(11011)) { println("HelloWorld") }
}