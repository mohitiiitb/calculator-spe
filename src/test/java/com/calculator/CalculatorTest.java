package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testSqrtValid() {
        assertEquals(4.0, Calculator.sqrt(16.0), 1e-12);
        assertEquals(0.0, Calculator.sqrt(0.0), 1e-12);
    }

    @Test
    void testSqrtInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.sqrt(-9.0));
    }

    @Test
    void testFactorialValid() {
        assertEquals(1, Calculator.fact(0));
        assertEquals(1, Calculator.fact(1));
        assertEquals(120, Calculator.fact(5));
    }

    @Test
    void testFactorialLarge() {
        assertEquals(2432902008176640000L, Calculator.fact(20));
    }

    @Test
    void testFactorialOverflow() {
        assertThrows(ArithmeticException.class, () -> Calculator.fact(21));
    }

    @Test
    void testFactorialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.fact(-5));
        assertThrows(IllegalArgumentException.class, () -> Calculator.fact(2.1));
        assertThrows(IllegalArgumentException.class, () -> Calculator.fact(-2.1));
    }

    @Test
    void testLnValid() {
        assertEquals(0.0, Calculator.ln(1.0), 1e-12);
        assertEquals(Math.log(10.0), Calculator.ln(10.0), 1e-12);
    }

    @Test
    void testLnInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.ln(0.0));
        assertThrows(IllegalArgumentException.class, () -> Calculator.ln(-2.0));
    }

    @Test
    void testPowValid() {
        assertEquals(1.0, Calculator.pow(5.0, 0), 1e-12);
        assertEquals(8.0, Calculator.pow(2.0, 3), 1e-12);
        assertEquals(0.25, Calculator.pow(2.0, -2), 1e-12);
        assertEquals(Math.pow(-2, 2), Calculator.pow(-2.0, 2), 1e-12); // negative base integer exponent
    }

    @Test
    void testPowFractionalExponentNegativeBase() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.pow(-2.0, 0.5));
        assertThrows(IllegalArgumentException.class, () -> Calculator.pow(-5.0, 1.5));
    }

    @Test
    void testPowNegativeBaseIntegerExponent() {
        assertEquals(-8.0, Calculator.pow(-2.0, 3), 1e-12);
    }

    @Test
    void testPowZeroBaseNegativeExponent() {
        assertEquals(Double.POSITIVE_INFINITY, Calculator.pow(0.0, -1), 1e-12);
    }
}
