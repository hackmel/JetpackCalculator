package com.example.jetpackcalculator.util

import androidx.core.text.isDigitsOnly

class EquationParser(val equation: String) {

    private var generatedOperatorToken = ArrayDeque<String>()
    private var generatedPostFixToken = ArrayDeque<String>()
    private var resultStack = ArrayDeque<String>()
    private data class Operator(var symbol: String, var precendence: Int)
    data class ValidationResult(var isSuccess: Boolean, var message: String)

    private var _characterValidationResult = ValidationResult(false, "")
    val characterValidationResult: ValidationResult get() = _characterValidationResult

    private var _parenthesisValidationResult = ValidationResult(false, "")
    val parenthesisValidationResult: ValidationResult get() = _parenthesisValidationResult

    private val validCharacters = listOf<String>("x","-","+","/", "*", ".", "(", ")")

    fun evaluate(): Float {
        val slicedEquation = equationSlicer(equation)

        slicedEquation.forEach {
            if (it.toFloatOrNull() != null) {
                generatedPostFixToken.add(it)
            } else {

                val operation = findOperator(it)
                operation?.let { enteredOpertor ->

                    if (generatedOperatorToken.isEmpty()) {
                        generatedOperatorToken.addFirst(enteredOpertor.symbol)
                    } else {

                        when (enteredOpertor.symbol) {
                            "(" -> generatedOperatorToken.add(enteredOpertor.symbol)
                            ")" -> {
                                var isDone = false
                                while (isDone == false) {

                                    if(generatedOperatorToken.last() == "(") {
                                        isDone = true
                                        //generatedOperatorToken.removeLast()
                                    }else {
                                        generatedPostFixToken.add(generatedOperatorToken.last())
                                    }
                                    generatedOperatorToken.removeLast()
                                }

                            }
                            else -> {
                                val lastOperator = generatedOperatorToken.last()
                                val lastOperatorTokenInStack = findOperator(lastOperator)

                                lastOperatorTokenInStack?.let {
                                    if (enteredOpertor.precendence <= it.precendence) {

                                        for(i in generatedOperatorToken.indices) {
                                            val lastToken = findOperator(generatedOperatorToken.last())
                                            lastToken?.let {token ->
                                                if ((token.symbol != "(") &&
                                                    (enteredOpertor.precendence <= token.precendence)
                                                ) {
                                                    generatedPostFixToken.add(token.symbol)
                                                    generatedOperatorToken.removeLast()
                                                }
                                            }

                                        }
                                        generatedOperatorToken.addLast(enteredOpertor.symbol)

                                    } else {
                                        generatedOperatorToken.addLast(enteredOpertor.symbol)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        popRemainingGeneratedOperatorTokens()

        return calculateResut()

    }

    private fun equationSlicer(equation: String): MutableList<String> {
        val equationStack = mutableListOf<String>()
        var digits = "";

        for (i in equation.length -1 downTo 0) {
            if (equation[i].isDigit() || equation[i].toString() == ".") {
                digits = digits.plus(equation[i])

                if (i == 0) {
                    digits = digits.reversed()
                    equationStack.add(digits)
                }
            } else {
                val operator = equation[i].toString()
                if (operator == "+" ||
                    operator == "-" ||
                    operator == "x" ||
                    operator == "*" ||
                    operator == "/" ||
                    operator == "(" ||
                    operator == ")"
                ) {

                    if(!digits.isEmpty()) {
                        digits = digits.reversed()
                        equationStack.add(digits)
                        digits = ""
                    }

                    if(operator == "(" && equationStack.last() == "-") {
                        equationStack.removeLast()
                        val lastNumber = equationStack.last()
                        equationStack.removeLast()
                        equationStack.add("-".plus(lastNumber))
                        val indexToRemove = equationStack.lastIndexOf(")")
                        equationStack.removeAt(indexToRemove)
                    }else {
                        equationStack.add(equation[i].toString())
                    }

                }
            }
        }

        equationStack.reverse();
        return equationStack
    }

    private fun calculateResut(): Float {
        while (!generatedPostFixToken.isEmpty()) {
            val token = generatedPostFixToken.first()

            if (token.toFloatOrNull() != null) {
                resultStack.addFirst(generatedPostFixToken.first())
                generatedPostFixToken.removeFirst()
            } else {
                val (firstDigit, secondDigit) = getPairsOfDigitsFromResultStack(resultStack)
                val result = calculateResultBasedFromPostFixOperatorToken(
                    firstDigit,
                    secondDigit,
                    generatedPostFixToken
                )

                result?.let {
                    resultStack.addFirst(it.toString())
                }

                generatedPostFixToken.removeFirst()
            }
        }

        return resultStack.last().toFloat()
    }

    private fun getPairsOfDigitsFromResultStack(resultStack:ArrayDeque<String>): Pair<Float, Float> {
        val secondDigit = resultStack.first().toFloat()
        resultStack.removeFirst()
        val firstDigit = resultStack.first().toFloat()
        resultStack.removeFirst()
        return Pair(firstDigit, secondDigit)
    }

    private fun calculateResultBasedFromPostFixOperatorToken(
        firstDigit: Float,
        secondDigit: Float,
        postFixToken:ArrayDeque<String>): Float? {
        val result = when (postFixToken.first()) {
            "+" -> firstDigit + secondDigit
            "-" -> firstDigit - secondDigit
            "x" -> firstDigit * secondDigit
            "/" -> firstDigit / secondDigit
            else -> null
        }
        return result
    }

    private fun popRemainingGeneratedOperatorTokens() {
        while (!generatedOperatorToken.isEmpty()) {
            generatedPostFixToken.add(generatedOperatorToken.last())
            generatedOperatorToken.removeLast()
        }
    }

    private fun findOperator(it: String): Operator? {
        val operation = when (it) {
            "+" -> Operator("+", 1)
            "x", "*" -> Operator("x", 2)
            "/" -> Operator("/", 2)
            "-" -> Operator("-", 1)
            "(" -> Operator("(", 0)
            ")" -> Operator(")", 0)
            else -> null
        }
        return operation
    }

    fun validateCharacters() {
        val trimmedEquation = equation.replace(" ", "")

        trimmedEquation.forEach {
            if(!it.isDigit()){
                if(it.lowercase().toString() !in validCharacters){
                    _characterValidationResult = ValidationResult(false, "Invalid character found: $it")
                    return
                }
            }
        }
        _characterValidationResult = ValidationResult(true, "Success")
    }

    fun validateParenthesis() {
        val parenthesis = mutableListOf<String>()
        var lastCharacter = ""
        val trimmedEquation = equation.replace(" ", "")

        trimmedEquation.forEach {
            if(it.toString() == "(" || it.toString() == ")") {
                if(it.toString() == ")") {
                    if(parenthesis.isEmpty()) {
                        _parenthesisValidationResult = ValidationResult(false, "Invalid parenthesis found")
                        return
                    }else {
                        parenthesis.removeLast()
                    }
                }else {
                    if(!lastCharacter.isEmpty()) {
                        if(lastCharacter.toFloatOrNull() != null){
                            _parenthesisValidationResult = ValidationResult(false, "A valid operator should follow an opening parethesis or a number")
                            return
                        }
                    }
                    parenthesis.add(it.toString())
                }
            }
            lastCharacter = it.toString()
        }

        if(parenthesis.size > 0) {
            _parenthesisValidationResult = ValidationResult(false, "Invalid parenthesis found")
        }else {
            _parenthesisValidationResult = ValidationResult(true, "Success")
        }
    }
}