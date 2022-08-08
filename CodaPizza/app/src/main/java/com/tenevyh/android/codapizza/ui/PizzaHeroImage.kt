package com.tenevyh.android.codapizza.ui

import android.media.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.tooling.preview.Preview
import com.tenevyh.android.codapizza.R
import com.tenevyh.android.codapizza.model.Pizza
import com.tenevyh.android.codapizza.model.Topping
import com.tenevyh.android.codapizza.model.ToppingPlacement

@Preview
@Composable
private fun PizzaHeroImagePreview() {
    PizzaHeroImage(
        pizza = Pizza(
            toppings = mapOf(
                Topping.Pineapple to ToppingPlacement.All,
                Topping.Pepperoni to ToppingPlacement.Left,
                Topping.Basil to ToppingPlacement.Right
            )
        )
    )
}



@Composable
fun PizzaHeroImage(
    pizza: Pizza,
    modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(R.drawable.pizza_crust),
        contentDescription = null,
        modifier = modifier
    )
}