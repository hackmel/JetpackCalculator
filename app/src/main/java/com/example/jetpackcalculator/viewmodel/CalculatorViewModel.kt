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

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState("", "0.00"))
        private set

    fun getCalculatorAction(calculatorAction: CalculatorAction) {

        when(calculatorAction) {
            is RemoveCommand -> {
                when(calculatorAction.type) {
                    CalculatorRemoveCommand.DEL -> {
                        if(!state.equation.isEmpty()) {
                            state = state.copy(equation = state.equation
                                .removeRange( state.equation.length - 1
                                    , state.equation.length)
                            )
                        }
                    }
                    else -> {
                        state = state.copy(equation = "", result = "0.0")
                    }
                }
            }
            is Number -> {
                when(calculatorAction.type) {
                    CalculatorNumeric.SIGN -> {
                        if (!state.equation.isEmpty()) {
                            val lastDigit = state.equation.last()
                            if (lastDigit.isDigit()) {
                                if (state.equation.endsWith("(-$lastDigit")) {
                                    state = state.copy(
                                        equation = state.equation
                                            .removeRange(
                                                state.equation.length - 3,
                                                state.equation.length
                                            )
                                            .plus(lastDigit)
                                    )
                                }else {
                                    state = state.copy(
                                        equation = state.equation
                                            .removeRange(
                                                state.equation.length - 1,
                                                state.equation.length
                                            )
                                            .plus("(-$lastDigit")
                                    )
                                }
                            }
                        }
                    }
                    else -> {
                        state = state.copy(equation = state.equation
                            .plus(calculatorAction.type.value)
                        )
                    }
                }
            }

            is Operation -> {
                when(calculatorAction.type) {
                    CalculatorOperator.EQUALS -> {
                        calculate()
                    }
                    else -> {
                        state = state.copy(equation = state.equation
                            .plus(calculatorAction.type.value)
                        )
                    }
                }
            }

            is Parethesis -> {
                state = state.copy(equation = state.equation
                    .plus(calculatorAction.type.value)
                )
            }
        }
    }

    private fun calculate() {
        val parser = EquationParser(state.equation)
        parser.validateCharacters()
        parser.validateParenthesis()
        if(parser.characterValidationResult.isSuccess &&
            parser.parenthesisValidationResult.isSuccess) {
            state = state.copy(result = parser.evaluate().toString())
        }else {
            state = state.copy(result = "There is a problem with the equation. Please try again")
        }
    }
}