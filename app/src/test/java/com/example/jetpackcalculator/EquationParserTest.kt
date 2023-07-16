package com.example.jetpackcalculator

import com.example.jetpackcalculator.util.EquationParser
import org.junit.Assert.assertEquals
import org.junit.Test


class EquationParserTest {

    @Test
    fun `Test adding 2 digits`(){
        //Arrange
        val eq = EquationParser("2+1")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(3f, result)
    }

    @Test
    fun `Test adding mutiple digits`(){
        //Arrange
        val eq = EquationParser("2+1+5+6")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(14f, result)
    }

    @Test
    fun `Test subtracting 2 digits`(){
        //Arrange
        val eq = EquationParser("2-1")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(1f, result)
    }

    @Test
    fun `Test subtracting mutiple digits`(){
        //Arrange
        val eq = EquationParser("20-1-5-6")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(8f, result)
    }

    @Test
    fun `Test multiplying 2 digits using x symbol`(){
        //Arrange
        val eq = EquationParser("235x2")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(470f, result)
    }

    @Test
    fun `Test multiplying 2 digits using asterisk * symbol`(){
        //Arrange
        val eq = EquationParser("235*2")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(470f, result)
    }

    @Test
    fun `Test multiplying mutiple digits using asterisk x symbol`(){
        //Arrange
        val eq = EquationParser("5x2x9x3")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(270f, result)
    }

    @Test
    fun `Test multiplying mutiple digits using asterisk * symbol`(){
        //Arrange
        val eq = EquationParser("5*2*9*3")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(270f, result)
    }


    @Test
    fun `Test dividing 2 digits`(){
        //Arrange
        val eq = EquationParser("15/2")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(7.5f, result)
    }

    @Test
    fun `Test dividing mutiple digits`(){
        //Arrange
        val eq = EquationParser("150/2/2")
        //Act
        val result = eq.evaluate()
        //Assert
        assertEquals(37.5f, result)
    }

    @Test
    fun `Test add and subtraction on mutiple digits`(){
        //Arrange
        var eq = EquationParser("150+2-2")
        //Act
        var result = eq.evaluate()
        //Assert
        assertEquals(150f, result)

        //Arrange
        eq = EquationParser("1-150+2+2")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(-145f, result)

        //Arrange
        eq = EquationParser("1-150+2+2+10+2-3-5-6")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(-147f, result)
    }

    @Test
    fun `Test all operations on mutiple digits`(){
        //Arrange
        var eq = EquationParser("150/2-2*5/2")
        //Act
        var result = eq.evaluate()
        //Assert
        assertEquals(70f, result)

        //Arrange
        eq = EquationParser("5-10/2+5")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(5f, result)

        //Arrange

        eq = EquationParser("2-1/150*2+2")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(3.9866667f, result)
    }

    @Test
    fun `Test all operations on mutiple digits with parenthesis`(){
        //Arrange
        var eq = EquationParser("150/(2-10)*5/2")
        //Act
        var result = eq.evaluate()
        //Assert
        assertEquals(-46.875f, result)

        //Arrange
        eq = EquationParser("(5-10)/2+5")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(2.5f, result)

        //Arrange
        eq = EquationParser("5-10/(2+5)/2")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(4.285714f, result)

        //Arrange
        eq = EquationParser("2-1/(150*2+2)/(234+35000)")
        result = eq.evaluate()
        assertEquals(1.9999999f, result)

        //Arrange
        eq = EquationParser("2-1/(150*2+2)/(234/35000)/100000/1")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(1.999995f, result)
    }

    @Test
    fun `Test negative signs operation`(){
        //Arrange
        var eq = EquationParser("(-5)+2")
        //Act
        var result = eq.evaluate()
        //Assert
        assertEquals(-3f, result)

        //Arrange
        eq = EquationParser("((-5)+1)+2")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(-2f, result)

        //Arrange
        eq = EquationParser("((-5)+(-1))+2")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(-4f, result)

        //Arrange
        eq = EquationParser("((-5)+(-.25))+2")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(-3.25f, result)

        //Arrange
        eq = EquationParser("((-5.25)-(-1.25))/(2.5000)")
        //Act
        result = eq.evaluate()
        //Assert
        assertEquals(-1.6f, result)
    }

    @Test
    fun `Validate invalid characters`(){
        //Arrange
        var eq = EquationParser("((-5.25)-(-1.25))/(2.500)")
        //Act
        eq.validateCharacters()
        //Assert
        assertEquals(true, eq.characterValidationResult.isSuccess)

        //Arrange
        eq = EquationParser("((-5.25)N(-1.25))/(2.500)")
        //Act
        eq.validateCharacters()
        //Assert
        assertEquals(false, eq.characterValidationResult.isSuccess)

        //Arrange
        eq = EquationParser("(a)")
        //Act
        eq.validateCharacters()
        //Assert
        assertEquals(false, eq.characterValidationResult.isSuccess)

        //Arrange
        eq = EquationParser("(a + 1)")
        //Act
        eq.validateCharacters()
        //Assert
        assertEquals(false, eq.characterValidationResult.isSuccess)
    }

    @Test
    fun `Validate invalid parenethesis`(){
        //Arrange
        var eq = EquationParser("(((())))")
        //Act
        eq.validateParenthesis()
        //Assert
        assertEquals(true, eq.parenthesisValidationResult.isSuccess)

        //Arrange
        eq = EquationParser("(((()))")
        //Act
        eq.validateParenthesis()
        //Assert
        assertEquals(false, eq.parenthesisValidationResult.isSuccess)

        //Arrange
        eq = EquationParser("(1+(2 (3+(5*2))))")
        //Act
        eq.validateParenthesis()
        //Assert
        assertEquals(false, eq.parenthesisValidationResult.isSuccess)
    }
}