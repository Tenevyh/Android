package com.tenevyh.android.codapizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.tenevyh.android.codapizza.model.Topping
import com.tenevyh.android.codapizza.model.ToppingPlacement
import com.tenevyh.android.codapizza.ui.ToppingCell

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToppingCell(topping = Topping.Pepperoni,
            placement = ToppingPlacement.Left,
            onClickListener = {})
        }
    }
}