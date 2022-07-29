package com.tenevyh.android.codapizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Row {
                Checkbox(checked = true,
                    onCheckedChange = {/*TODO */ })
                Column {
                    Text(text = "Pineapple")
                    Text(text = "Whole pizza")
                }
            }
        }
    }
}