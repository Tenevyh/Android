package com.tenevyh.android.codapizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.tenevyh.android.codapizza.model.Topping
import com.tenevyh.android.codapizza.model.ToppingPlacement
import com.tenevyh.android.codapizza.ui.AppTheme
import com.tenevyh.android.codapizza.ui.PizzaBuilderScreen
import com.tenevyh.android.codapizza.ui.ToppingCell

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PizzaBuilderScreen()
            }
        }
    }
}