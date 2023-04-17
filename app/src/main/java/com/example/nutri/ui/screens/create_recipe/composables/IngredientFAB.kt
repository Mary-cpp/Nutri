package com.example.nutri.ui.screens.create_recipe.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IngredientFAB(scope: CoroutineScope, bottomSheetState: ModalBottomSheetState) {
    ExtendedFloatingActionButton(
        onClick = {
            scope.launch {
                bottomSheetState.show()
            }
        },
        text = { Text(text = "Add ingredient", fontSize = MaterialTheme.typography.subtitle2.fontSize)},
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(4.dp),
        icon ={
        Icon(
            ImageVector.vectorResource(id = R.drawable.add48px),
            contentDescription = "AddFAB",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    })
}