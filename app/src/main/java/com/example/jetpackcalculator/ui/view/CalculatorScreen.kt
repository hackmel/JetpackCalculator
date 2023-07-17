package com.example.jetpackcalculator.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcalculator.model.CalculatorAction
import com.example.jetpackcalculator.model.CalculatorNumeric
import com.example.jetpackcalculator.model.CalculatorState
import com.example.jetpackcalculator.ui.view.components.CalculatorTextInput

@Composable
fun CalculatorScreen(state: CalculatorState,
                     onClickEvent: (value: CalculatorAction)-> Unit,
                     modifier: Modifier = Modifier) {
    Column(modifier= Modifier.fillMaxWidth()) {
        CalculatorTextInput(state)
        CalculatorButtonContainer(onClickAction = onClickEvent )
    }
}

@Composable
@Preview
fun CalculatorScreenPreview() {
    CalculatorScreen(CalculatorState("","0.00"),
        {(CalculatorAction.Number(CalculatorNumeric.NINE))})
}