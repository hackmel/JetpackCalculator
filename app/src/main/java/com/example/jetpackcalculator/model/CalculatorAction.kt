package com.example.jetpackcalculator.model

sealed class CalculatorAction {
    class Operation(val type: CalculatorOperator): CalculatorAction()
    class Parethesis(val type: CalculatorParenthesis): CalculatorAction()
    class Number(val type: CalculatorNumeric): CalculatorAction()
    class RemoveCommand(val type: CalculatorRemoveCommand): CalculatorAction()

}

enum class CalculatorOperator (val value: String) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("x"),
    DIVDE("/"),
    EQUALS("="),
}

enum class CalculatorParenthesis (val value: String) {
    OPEN("("),
    CLOSE(")"),
}

enum class CalculatorRemoveCommand (val value: String) {
    CLEAR("C"),
    DEL("DEL"),
}

enum class CalculatorNumeric (val value: String) {
    SIGN("+/-"),
    DOT("."),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
}