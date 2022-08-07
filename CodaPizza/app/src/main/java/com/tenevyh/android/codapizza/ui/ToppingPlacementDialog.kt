package com.tenevyh.android.codapizza.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tenevyh.android.codapizza.R
import com.tenevyh.android.codapizza.model.Topping

@Composable
fun ToppingPlacementDialog(topping: Topping,
                           onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = onDismissRequest) {
        Card{
            Column {
                val toppingName = stringResource(topping.toppingName)
                Text(text = stringResource(R.string.placement_prompt, toppingName),
                style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp))
            }
        }
    }
}