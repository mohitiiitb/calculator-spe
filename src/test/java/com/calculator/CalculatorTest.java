package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testSqrtValid() {
        assertEquals(3.0, Calculator.sqrt(9.0), 1e-12);
        assertEquals(0.0, Calculator.sqrt(0.0), 1e-12);
    }

    @Test
    void testSqrtInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.sqrt(-1.0));
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
    }

    @Test
    void testLnValid() {
        assertEquals(0.0, Calculator.ln(1.0), 1e-12);
        assertEquals(Math.log(10.0), Calculator.ln(10.0), 1e-12);
    }

    @Test
    void testLnInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.ln(0.0));
        assertThrows(IllegalArgumentException.class, () -> Calculator.ln(-1.0));
    }

    @Test
    void testPowerValid() {
        assertEquals(1.0, Calculator.pow(5.0, 0), 1e-12);
        assertEquals(25.0, Calculator.pow(5.0, 2), 1e-12);
        assertEquals(0.0, Calculator.pow(0.0, 5), 1e-12);
    }

    @Test
    void testPowerInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.pow(-2.0, 3));
    }
}
