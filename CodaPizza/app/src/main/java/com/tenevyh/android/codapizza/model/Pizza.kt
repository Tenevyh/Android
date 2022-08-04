package com.tenevyh.android.codapizza.model

data class Pizza(val toppings: Map<Topping, ToppingPlacement> = emptyMap()){

    val price: Double get() = 9.99 + toppings.asSequence().sumOf{ (_, toppingPlacement) ->
        when (toppingPlacement) {
            ToppingPlacement.Left, ToppingPlacement.Right -> 0.5
            ToppingPlacement.All -> 1.0
        }
    }

    fun withTopping(topping: Topping, placement: ToppingPlacement?): Pizza{
        return copy(
            toppings = if (placement == null) {
                toppings - topping
            } else {
                toppings + (topping to placement)
            }
        )
    }
}
