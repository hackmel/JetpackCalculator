package com.example.jetpackcalculator.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcalculator.model.CalculatorAction
import com.example.jetpackcalculator.model.CalculatorAction.RemoveCommand
import com.example.jetpackcalculator.model.CalculatorAction.Parethesis
import com.example.jetpackcalculator.model.CalculatorAction.Number
import com.example.jetpackcalculator.model.CalculatorAction.Operation
import com.example.jetpackcalculator.model.CalculatorNumeric
import com.example.jetpackcalculator.model.CalculatorParenthesis
import com.example.jetpackcalculator.model.CalculatorOperator
import com.example.jetpackcalculator.model.CalculatorRemoveCommand
import com.example.jetpackcalculator.model.CalculatorState
import com.example.jetpackcalculator.ui.view.components.CalculatorButton

@Composable
fun CalculatorButtonContainer(modifier: Modifier = Modifier,
                              onClickAction: (value: CalculatorAction) -> Unit) {

    Column(
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            CalculatorButton(label = "C", {
                onClickAction(RemoveCommand(CalculatorRemoveCommand.CLEAR))
            })
            CalculatorButton(label = "(", {
                onClickAction(Parethesis(CalculatorParenthesis.OPEN))
            })
            CalculatorButton(label = ")", {
                onClickAction(Parethesis(CalculatorParenthesis.CLOSE)) })
            CalculatorButton(label = "/", {
                onClickAction(Operation(CalculatorOperator.DIVDE))
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            CalculatorButton(label = "7",  {
                onClickAction(Number(CalculatorNumeric.SEVEN))
            })
            CalculatorButton(label = "8", {
                onClickAction(Number(CalculatorNumeric.EIGHT))
            })
            CalculatorButton(label = "9", {
                onClickAction(Number(CalculatorNumeric.NINE))
            })
            CalculatorButton(label = "X", {
                onClickAction(Operation(CalculatorOperator.MULTIPLY))
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            CalculatorButton(label = "4", {
                onClickAction(Number(CalculatorNumeric.FOUR))
            })
            CalculatorButton(label = "5", {
                onClickAction(Number(CalculatorNumeric.FIVE))
            })
            CalculatorButton(label = "6", {
                onClickAction(Number(CalculatorNumeric.SIX))
            })
            CalculatorButton(label = "-", {
                onClickAction(Operation(CalculatorOperator.MINUS))
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            CalculatorButton(label = "1", {
                onClickAction(Number(CalculatorNumeric.ONE))
            })
            CalculatorButton(label = "2", {
                onClickAction(Number(CalculatorNumeric.TWO))
            })
            CalculatorButton(label = "3", {
                onClickAction(Number(CalculatorNumeric.THREE))
            })
            CalculatorButton(label = "+", {
                onClickAction(Operation(CalculatorOperator.PLUS))
            })
        }

        Row(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            CalculatorButton(label = "0", {
                onClickAction(Number(CalculatorNumeric.ZERO))
            })
            CalculatorButton(label = ".", {
                onClickAction(Number(CalculatorNumeric.DOT))
            })
            CalculatorButton(label = "=", {
                onClickAction(Operation(CalculatorOperator.EQUALS))
            })

        }

        Row(modifier = Modifier.padding(10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            CalculatorButton(label = "+/-", {
                onClickAction(Number(CalculatorNumeric.SIGN))
            })

            CalculatorButton(label = "DEL", {
                onClickAction(RemoveCommand(CalculatorRemoveCommand.DEL))
            })
        }
    }
}

@Composable
@Preview
fun CalculatorButtonContainerPreview() {
    CalculatorButtonContainer(onClickAction = {(Number(CalculatorNumeric.NINE))})
}