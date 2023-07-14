package com.example.jetpackcalculator.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpackcalculator.model.CalculatorAction
import com.example.jetpackcalculator.model.CalculatorState
import com.example.jetpackcalculator.ui.view.components.CalculatorTextInput

@Composable
fun CalculatorScreen(state: CalculatorState,
                     onClickEvent: (value: CalculatorAction)-> Unit,
                     modifier: Modifier = Modifier) {
    Column {
        CalculatorTextInput(state)
        CalculatorButtonContainer(onClickAction = onClickEvent )
    }
}