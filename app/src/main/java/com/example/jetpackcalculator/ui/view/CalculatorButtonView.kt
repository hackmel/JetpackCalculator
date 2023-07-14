package com.example.jetpackcalculator.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpackcalculator.model.CalculatorAction
import com.example.jetpackcalculator.model.CalculatorAction.RemoveCommand
import com.example.jetpackcalculator.model.CalculatorAction.Parethesis
import com.example.jetpackcalculator.model.CalculatorAction.Number
import com.example.jetpackcalculator.model.CalculatorAction.Operation
import com.example.jetpackcalculator.model.CalculatorNumeric
import com.example.jetpackcalculator.model.CalculatorParenthesis
import com.example.jetpackcalculator.model.CalculatorOperator
import com.example.jetpackcalculator.model.CalculatorRemoveCommand
import com.example.jetpackcalculator.ui.view.components.CalculatorButton



@Composable
fun CalculatorButtonContainer(modifier: Modifier = Modifier,
                              onClickAction: (value: CalculatorAction) -> Unit) {

    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
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

        Row() {
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

        Row() {
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

        Row() {
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

        Row() {
            CalculatorButton(label = "+/-", {
                onClickAction(Number(CalculatorNumeric.SIGN))
            })
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

        Row() {
            CalculatorButton(label = "DEL", {
                onClickAction(RemoveCommand(CalculatorRemoveCommand.DEL))
            })
        }
    }
}