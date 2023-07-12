package com.example.jetpackcalculator

import com.example.jetpackcalculator.util.EquationParser
import org.junit.Assert.assertEquals
import org.junit.Test


class EquationParserTest {

    @Test
    fun `Test adding 2 digits`(){
        val eq = EquationParser("2+1")
        val result = eq.evaluate()
        assertEquals(3f, result)
    }

    @Test
    fun `Test adding mutiple digits`(){
        val eq = EquationParser("2+1+5+6")
        val result = eq.evaluate()
        assertEquals(14f, result)
    }

    @Test
    fun `Test subtracting 2 digits`(){
        val eq = EquationParser("2-1")
        val result = eq.evaluate()
        assertEquals(1f, result)
    }

    @Test
    fun `Test subtracting mutiple digits`(){
        val eq = EquationParser("20-1-5-6")
        val result = eq.evaluate()
        assertEquals(8f, result)
    }

    @Test
    fun `Test multiplying 2 digits using x symbol`(){
        val eq = EquationParser("235x2")
        val result = eq.evaluate()
        assertEquals(470f, result)
    }

    @Test
    fun `Test multiplying 2 digits using asterisk * symbol`(){
        val eq = EquationParser("235*2")
        val result = eq.evaluate()
        assertEquals(470f, result)
    }

    @Test
    fun `Test multiplying mutiple digits using asterisk x symbol`(){
        val eq = EquationParser("5x2x9x3")
        val result = eq.evaluate()
        assertEquals(270f, result)
    }

    @Test
    fun `Test multiplying mutiple digits using asterisk * symbol`(){
        val eq = EquationParser("5*2*9*3")
        val result = eq.evaluate()
        assertEquals(270f, result)
    }


    @Test
    fun `Test dividing 2 digits`(){
        val eq = EquationParser("15/2")
        val result = eq.evaluate()
        assertEquals(7.5f, result)
    }

    @Test
    fun `Test dividing mutiple digits`(){
        val eq = EquationParser("150/2/2")
        val result = eq.evaluate()
        assertEquals(37.5f, result)
    }

    @Test
    fun `Test add and subtraction on mutiple digits`(){
        var eq = EquationParser("150+2-2")
        var result = eq.evaluate()
        assertEquals(150f, result)

        eq = EquationParser("1-150+2+2")
        result = eq.evaluate()
        assertEquals(-145f, result)

        eq = EquationParser("1-150+2+2+10+2-3-5-6")
        result = eq.evaluate()
        assertEquals(-147f, result)
    }

    @Test
    fun `Test all operations on mutiple digits`(){
        var eq = EquationParser("150/2-2*5/2")
        var result = eq.evaluate()
        assertEquals(70f, result)

        eq = EquationParser("5-10/2+5")
        result = eq.evaluate()
        assertEquals(5f, result)

        eq = EquationParser("2-1/150*2+2")
        result = eq.evaluate()
        assertEquals(3.9866667f, result)

    }

    @Test
    fun `Test all operations on mutiple digits with parenthesis`(){
        var eq = EquationParser("150/(2-10)*5/2")
        var result = eq.evaluate()
        assertEquals(-46.875f, result)

        eq = EquationParser("(5-10)/2+5")
        result = eq.evaluate()
        assertEquals(2.5f, result)

        eq = EquationParser("5-10/(2+5)/2")
        result = eq.evaluate()
        assertEquals(4.285714f, result)

        eq = EquationParser("2-1/(150*2+2)/(234+35000)")
        result = eq.evaluate()
        assertEquals(1.9999999f, result)

        eq = EquationParser("2-1/(150*2+2)/(234/35000)/100000/1")
        result = eq.evaluate()
        assertEquals(1.999995f, result)


    }

    @Test
    fun `Test negative signs operation`(){
        var eq = EquationParser("(-5)+2")
        var result = eq.evaluate()
        assertEquals(-3f, result)

        eq = EquationParser("((-5)+1)+2")
        result = eq.evaluate()
        assertEquals(-2f, result)

        eq = EquationParser("((-5)+(-1))+2")
        result = eq.evaluate()
        assertEquals(-4f, result)

        eq = EquationParser("((-5)+(-.25))+2")
        result = eq.evaluate()
        assertEquals(-3.25f, result)

        eq = EquationParser("((-5.25)-(-1.25))/(2.5000)")
        result = eq.evaluate()
        assertEquals(-1.6f, result)
    }

    @Test
    fun `Validate invalid characters`(){
        var eq = EquationParser("((-5.25)-(-1.25))/(2.500)")
        eq.validateCharacters()
        assertEquals(true, eq.characterValidationResult.isSuccess)

        eq = EquationParser("((-5.25)N(-1.25))/(2.500)")
        eq.validateCharacters()
        assertEquals(false, eq.characterValidationResult.isSuccess)

        eq = EquationParser("(a)")
        eq.validateCharacters()
        assertEquals(false, eq.characterValidationResult.isSuccess)

        eq = EquationParser("(a + 1)")
        eq.validateCharacters()
        assertEquals(false, eq.characterValidationResult.isSuccess)
    }

    @Test
    fun `Validate invalid parenethesis`(){
         var eq = EquationParser("(((())))")
         eq.validateParenthesis()
         assertEquals(true, eq.parenthesisValidationResult.isSuccess)

        eq = EquationParser("(((()))")
        eq.validateParenthesis()
        assertEquals(false, eq.parenthesisValidationResult.isSuccess)

        eq = EquationParser("(1+(2 (3+(5*2))))")
        eq.validateParenthesis()
        assertEquals(false, eq.parenthesisValidationResult.isSuccess)

    }
}