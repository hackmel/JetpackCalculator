package com.example.jetpackcalculator.ui.view.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetpackcalculator.model.CalculatorState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorTextInput(value: CalculatorState) {
    var text = value
    OutlinedTextField(value = text.equation,
        onValueChange = {  it ->
            text.equation = it
        },
        label = {Text(text = "Enter equation")},
        placeholder = {Text(text = "Enter equation")}
    )
    Text(text = value.result)
}