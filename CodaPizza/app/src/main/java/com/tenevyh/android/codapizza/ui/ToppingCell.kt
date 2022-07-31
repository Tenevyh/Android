package com.tenevyh.android.codapizza.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tenevyh.android.codapizza.model.Topping
import com.tenevyh.android.codapizza.model.ToppingPlacement

@Preview
@Composable
private fun ToppingCellPreviewNotOnPizza() {
    ToppingCell(topping = Topping.Pepperoni,
        placement = null,
        onClickListener = {})
}

@Preview
@Composable
private fun ToppingCellPreviewOnLeftHalf() {
    ToppingCell(topping = Topping.Pepperoni,
        placement = ToppingPlacement.Left,
        onClickListener = {})
}

@Composable
fun ToppingCell(topping: Topping, placement: ToppingPlacement?, onClickListener: () -> Unit) {
    Row {
        Checkbox(checked = (placement != null),
            onCheckedChange = {/*TODO */ })
        Column {
            Text(text = stringResource(topping.toppingName))
            if (placement != null) {
                Text(text = stringResource(placement.label))
            }
        }
    }
}