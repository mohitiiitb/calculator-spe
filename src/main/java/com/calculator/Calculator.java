package com.calculator;

public class Calculator {
    public static double sqrt(double x) {
        if (x < 0) throw new IllegalArgumentException("Square root of negative number is undefined.");
        return Math.sqrt(x);
    }

    public static long fact(long n) {
        if (n < 0) throw new IllegalArgumentException("Factorial of negative number is undefined.");
        long result = 1;
        for (long i = 2; i <= n; i++) {
            long next = result * i;
            if (next / i != result) throw new ArithmeticException("Factorial result overflows a long.");
            result = next;
        }
        return result;
    }

    public static double ln(double x) {
        if (x <= 0) throw new IllegalArgumentException("Natural log undefined for zero or negative values.");
        return Math.log(x);
    }

    public static double pow(double x, long b) {
        if (x < 0) throw new IllegalArgumentException("Negative base not allowed.");
        return Math.pow(x, b);
    }
}
