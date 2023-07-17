package com.example.jetpackcalculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpackcalculator.model.CalculatorAction
import com.example.jetpackcalculator.model.CalculatorAction.RemoveCommand
import com.example.jetpackcalculator.model.CalculatorAction.Parethesis
import com.example.jetpackcalculator.model.CalculatorAction.Number
import com.example.jetpackcalculator.model.CalculatorAction.Operation
import com.example.jetpackcalculator.model.CalculatorNumeric
import com.example.jetpackcalculator.model.CalculatorOperator
import com.example.jetpackcalculator.model.CalculatorRemoveCommand
import com.example.jetpackcalculator.model.CalculatorState
import com.example.jetpackcalculator.util.EquationParser
import java.text.DecimalFormat

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState("", "0.00"))
        private set

    fun getCalculatorAction(calculatorAction: CalculatorAction) {
        when(calculatorAction) {
            is RemoveCommand -> {
                when(calculatorAction.type) {
                    CalculatorRemoveCommand.DEL -> {
                        deleteCharacterFromEquation()
                    }
                    else -> {
                        clearValues()
                    }
                }
            }
            is Number -> {
                when(calculatorAction.type) {
                    CalculatorNumeric.SIGN -> {
                        if (!state.equation.isEmpty()) {
                            val lastDigit = state.equation.last()
                            if (lastDigit.isDigit()) {
                                if (isNegative()) {
                                    turnNegativeNumberToPositive(lastDigit)
                                }else {
                                    turnPositiveNumberToNegative(lastDigit)
                                }
                            }
                        }
                    }
                    else -> {
                        generateEquationFromAction(calculatorAction)
                    }
                }
            }
            is Operation -> {
                when(calculatorAction.type) {
                    CalculatorOperator.EQUALS -> {
                        calculate()
                    }
                    else -> {
                        generateEquationFromAction(calculatorAction)
                    }
                }
            }
            is Parethesis -> {
                generateEquationFromAction(calculatorAction)
            }
        }
    }

    private fun clearValues() {
        state = state.copy(equation = "", result = "0.0")
    }

    private fun isNegative(): Boolean{
        val  equation = state.equation
        for (i in state.equation.length -1 downTo 0) {
            if(!equation[i].isDigit() &&
                equation[i].toString() != ".") {
                return (i > 0 &&
                        equation[i].toString() == "-"
                        && equation[i-1].toString() =="("
                        )
            }
        }

        return false
    }

    private fun parseLastDigit():Pair<Int, String> {
        val  equation = state.equation
        var parsedDigit = ""
        var index = 0
        for (i in state.equation.length -1 downTo 0) {
            if(equation[i].isDigit() || equation[i].toString() == ".") {
                parsedDigit = equation[i].plus(parsedDigit)
            }else {
                index = i + 1
                break
            }
        }
        return Pair(index, parsedDigit)
    }
    private fun turnPositiveNumberToNegative(lastDigit: Char) {
        val (index,parsedDigit) = parseLastDigit()
        state = state.copy(
            equation = state.equation
                .removeRange(
                    index,
                    state.equation.length
                )
                .plus("(-$parsedDigit")
        )
    }

    private fun turnNegativeNumberToPositive(lastDigit: Char) {
        val (index,parsedDigit) = parseLastDigit()
        state = state.copy(
            equation = state.equation
                .removeRange(
                    index - 2,
                    state.equation.length
                )
                .plus(parsedDigit)
        )
    }

    private fun generateEquationFromAction(calculatorAction: CalculatorAction) {
        val value = when(calculatorAction) {
            is Number -> calculatorAction.type.value
            is Parethesis -> calculatorAction.type.value
            is RemoveCommand -> calculatorAction.type.value
            is Operation -> calculatorAction.type.value
        }
        state = state.copy(
            equation = state.equation
                .plus(value)
        )
    }

    private fun deleteCharacterFromEquation() {
        if (!state.equation.isEmpty()) {
            state = state.copy(
                equation = state.equation
                    .removeRange(
                        state.equation.length - 1, state.equation.length
                    )
            )
        }
    }

    private fun calculate() {
        val numberFormat = DecimalFormat("#,###.00")
        val parser = EquationParser(state.equation)
        parser.validateCharacters()
        parser.validateParenthesis()
        if(parser.characterValidationResult.isSuccess &&
            parser.parenthesisValidationResult.isSuccess) {
            state = state.copy(result = numberFormat.format(parser.evaluate()))
        }else {
            state = state.copy(result = "There is a problem with the equation. Please try again")
        }
    }
}