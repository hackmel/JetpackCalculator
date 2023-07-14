package com.example.jetpackcalculator.ui.view.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalculatorButton(label: String,
                     onClickEvent: ()-> Unit,
                     modifier: Modifier = Modifier) {
    Button(
        onClick = onClickEvent,
    ) {
        Text(text = label)
    }
}