package com.example.nutri.ui.screens.my_recipes.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.nutri.R

@Composable
fun SortAndFilter(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly){
        Button(onClick = { /*TODO(Sort)*/ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondaryVariant),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.elevation(4.dp),
            content = {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.sort48px),
                    tint = Color.Black,
                    contentDescription = "SortIcon",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp))

                Text(text = "Sort", color = Color.Black, modifier = Modifier.padding(end = 16.dp))
            }
        )

        Button(onClick = { /*TODO(Filter)*/ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.elevation(4.dp)
        ) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.filter_alt48px),
                tint = Color.Black,
                contentDescription = "SortIcon",
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp))
            Text(text = "Filter", color = Color.Black)
        }
    }
}