package com.example.nutri.ui.screens.home.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FAB(
    modifier : Modifier = Modifier,
    color : Color = MaterialTheme.colors.primary,
    onClick: () -> Unit,
    elevation: Dp = 4.dp,
    border: BorderStroke? = null,
    shape: Shape = NutriShape.smallRoundCornerShape,
    text: String? = null,
    @DrawableRes iconRes: Int
){
    Surface(
        onClick = onClick,
        modifier = modifier
            .sizeIn(
                minHeight = FabSize,
                minWidth = FabSize
            )
            .semantics { role = Role.Button },
        shape = shape,
        color = color,
        elevation = elevation,
        border = border,
    ) {
        val startPadding = if (text == null) FabIconPadding else FabTextPadding
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = startPadding, end = startPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon (
                imageVector = ImageVector.vectorResource(id = iconRes),
                contentDescription = text.plus(" FAB"),
                modifier = modifier.size(24.dp),
                tint = Color.White
            )
            if (text != null){
                Spacer(modifier = Modifier.width(FabIconPadding))
                Text(
                    text = text,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    modifier = Modifier.padding(start = 2.dp, end = 6.dp)
                )
            }
        }
    }
}


private val FabSize = 48.dp
private val FabIconPadding = 12.dp
private val FabTextPadding = 20.dp

@Preview
@Composable
fun FabPreview(){
    NutriTheme {
        FAB(
            onClick = { /*TODO*/ },
            modifier = Modifier.wrapContentSize(),
            border = BorderStroke(1.dp, Color.White),
            iconRes = R.drawable.add48px,
            text = "Add ingredient"
        )
    }
}