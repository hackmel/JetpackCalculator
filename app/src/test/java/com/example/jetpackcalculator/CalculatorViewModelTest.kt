package com.example.jetpackcalculator

import com.example.jetpackcalculator.model.CalculatorAction.Parethesis
import com.example.jetpackcalculator.model.CalculatorAction.Operation
import com.example.jetpackcalculator.model.CalculatorAction.Number
import com.example.jetpackcalculator.model.CalculatorNumeric.ONE
import com.example.jetpackcalculator.model.CalculatorNumeric.TWO
import com.example.jetpackcalculator.model.CalculatorNumeric.THREE
import com.example.jetpackcalculator.model.CalculatorNumeric.FOUR
import com.example.jetpackcalculator.model.CalculatorNumeric.FIVE
import com.example.jetpackcalculator.model.CalculatorNumeric.SIX
import com.example.jetpackcalculator.model.CalculatorNumeric.NINE
import com.example.jetpackcalculator.model.CalculatorNumeric.SIGN
import com.example.jetpackcalculator.model.CalculatorNumeric.ZERO
import com.example.jetpackcalculator.model.CalculatorNumeric.DOT
import com.example.jetpackcalculator.model.CalculatorOperator.PLUS
import com.example.jetpackcalculator.model.CalculatorOperator.MINUS
import com.example.jetpackcalculator.model.CalculatorOperator.MULTIPLY
import com.example.jetpackcalculator.model.CalculatorOperator.DIVDE
import com.example.jetpackcalculator.model.CalculatorOperator.EQUALS
import com.example.jetpackcalculator.model.CalculatorParenthesis.OPEN
import com.example.jetpackcalculator.model.CalculatorParenthesis.CLOSE

import com.example.jetpackcalculator.viewmodel.CalculatorViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorViewModelTest {

    val viewModel = CalculatorViewModel()
    val equals = Operation(EQUALS)
    val plus = Operation(PLUS)
    val minus = Operation(MINUS)
    val multiply = Operation(MULTIPLY)
    val divide = Operation(DIVDE)

    val zero = Number(ZERO)
    val one = Number(ONE)
    val two = Number(TWO)
    val three = Number(THREE)
    val four = Number(FOUR)
    val five = Number(FIVE)
    val six = Number(SIX)
    val nine = Number(NINE)

    val open = Parethesis(OPEN)
    val close = Parethesis(CLOSE)

    val negative = Number(SIGN)
    val dot = Number(DOT)

    @Test
    fun `3+2`() {
        //Arrange
        viewModel.getCalculatorAction(three)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("3+2", viewModel.state.equation)
        assertEquals("5.0", viewModel.state.result)
    }

    @Test
    fun `2+1+5+6`() {
        //Arrange
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(six)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("2+1+5+6", viewModel.state.equation)
        assertEquals("14.0", viewModel.state.result)
    }

    @Test
    fun `2-1`() {
        //Arrange
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("2-1", viewModel.state.equation)
        assertEquals("1.0", viewModel.state.result)
    }

    @Test
    fun `20-1-5-6`() {
        //Arrange
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(six)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("20-1-5-6", viewModel.state.equation)
        assertEquals("8.0", viewModel.state.result)
    }

    @Test
    fun `235x2`() {
        //Arrange
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(three)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("235x2", viewModel.state.equation)
        assertEquals("470.0", viewModel.state.result)
    }

    @Test
    fun `5x2x9x3`() {
        //Arrange
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(nine)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(three)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("5x2x9x3", viewModel.state.equation)
        assertEquals("270.0", viewModel.state.result)
    }

    @Test
    fun `15 divide 2`() {
        //Arrange
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("15/2", viewModel.state.equation)
        assertEquals("7.5", viewModel.state.result)
    }

    @Test
    fun `150 divide 2 divide 2`() {
        //Arrange
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("150/2/2", viewModel.state.equation)
        assertEquals("37.5", viewModel.state.result)
    }

    @Test
    fun `150+2-2`() {
        //Arrange
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("150+2-2", viewModel.state.equation)
        assertEquals("150.0", viewModel.state.result)
    }

    @Test
    fun `1-150+2+2+10+2-3-5-6`() {
        //Arrange
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(three)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(six)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("1-150+2+2+10+2-3-5-6", viewModel.state.equation)
        assertEquals("-147.0", viewModel.state.result)
    }

    @Test
    fun `1-150+2+2`() {
        //Arrange
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)
        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("1-150+2+2", viewModel.state.equation)
        assertEquals("-145.0", viewModel.state.result)
    }

    @Test
    fun `150 divide 2-2*5 divide 2`() {
        //Arrange
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("150/2-2x5/2", viewModel.state.equation)
        assertEquals("70.0", viewModel.state.result)
    }

    @Test
    fun `5-10 divide 2+5`() {
        //Arrange

        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(five)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("5-10/2+5", viewModel.state.equation)
        assertEquals("5.0", viewModel.state.result)
    }


    @Test
    fun `2-1 divide 150*2+2`() {
        //Arrange

        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("2-1/150x2+2", viewModel.state.equation)
        assertEquals("3.9866667", viewModel.state.result)
    }

    @Test
    fun `150 divide (2-10)*5 divide 2`() {
        //Arrange
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("150/(2-10)x5/2", viewModel.state.equation)
        assertEquals("-46.875", viewModel.state.result)
    }

    @Test
    fun `(5-10) divide 2+5`() {
        //Arrange
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(five)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("(5-10)/2+5", viewModel.state.equation)
        assertEquals("2.5", viewModel.state.result)
    }

    @Test
    fun `5-10 divide (2+5) divide 2`() {
        //Arrange
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("5-10/(2+5)/2", viewModel.state.equation)
        assertEquals("4.285714", viewModel.state.result)
    }

    @Test
    fun `2-1 divide (150*2+2) divide (234+35000)`() {
        //Arrange
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(three)
        viewModel.getCalculatorAction(four)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(three)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(close)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("2-1/(150x2+2)/(234+35000)", viewModel.state.equation)
        assertEquals("1.9999999", viewModel.state.result)
    }

    @Test
    fun `2-1 divide (150*2+2) divide (234 divide 35000) divide 100000 divide 1`() {
        //Arrange
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(minus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(multiply)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(three)
        viewModel.getCalculatorAction(four)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(three)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(zero)
        viewModel.getCalculatorAction(divide)
        viewModel.getCalculatorAction(one)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("2-1/(150x2+2)/(234/35000)/100000/1", viewModel.state.equation)
        assertEquals("1.999995", viewModel.state.result)
    }

    @Test
    fun `(-5)+2`() {
        //Arrange
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(negative)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("(-5)+2", viewModel.state.equation)
        assertEquals("-3.0", viewModel.state.result)
    }

    @Test
    fun `((-5)+1)+2`() {
        //Arrange
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(negative)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("((-5)+1)+2", viewModel.state.equation)
        assertEquals("-2.0", viewModel.state.result)
    }

    @Test
    fun `((-5)+(-1))+2`() {
        //Arrange
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(negative)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(one)
        viewModel.getCalculatorAction(negative)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("((-5)+(-1))+2", viewModel.state.equation)
        assertEquals("-4.0", viewModel.state.result)
    }

    @Test
    fun `((-5)+(-dot25))+2`() {
        //Arrange
        viewModel.getCalculatorAction(open)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(negative)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(dot)
        viewModel.getCalculatorAction(two)
        viewModel.getCalculatorAction(five)
        viewModel.getCalculatorAction(negative)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(close)
        viewModel.getCalculatorAction(plus)
        viewModel.getCalculatorAction(two)

        //Act
        viewModel.getCalculatorAction(equals)

        //Assert
        assertEquals("((-5)+(-.25))+2", viewModel.state.equation)
        assertEquals("-3.25", viewModel.state.result)
    }
}
