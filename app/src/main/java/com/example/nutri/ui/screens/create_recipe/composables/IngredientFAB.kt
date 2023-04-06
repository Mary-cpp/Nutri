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
    FloatingActionButton(
        onClick = {
            scope.launch {
                bottomSheetState.show()
            }
        },
        modifier = Modifier.size(56.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(4.dp)
    ) {
        Icon(
            ImageVector.vectorResource(id = R.drawable.add48px),
            contentDescription = "AddFAB",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }
}