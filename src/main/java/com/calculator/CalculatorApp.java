package com.calculator;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class CalculatorApp {
    private static final DecimalFormat df = new DecimalFormat("0.0000");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("\n=== CALCULATOR ===");
        System.out.println("1: Square root [sqrt(x)]");
        System.out.println("2: Factorial [fact(x)]");
        System.out.println("3: Natural log [ln(x)]");
        System.out.println("4: Power [pow(x,b)]");
        System.out.println("5: Exit");

        while (running) {
            System.out.print("\nChoose an option: ");

            int choice;
            try {
                choice = sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1-5.");
                sc.next();
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter x: ");
                        double x1 = sc.nextDouble();
                        System.out.println("Result: sqrt(x) = " + df.format(Calculator.sqrt(x1)));
                        break;
                    case 2:
                        System.out.print("Enter x: ");
                        long x2 = sc.nextLong();
                        System.out.println("Result: fact(x) = " + Calculator.fact(x2));
                        break;
                    case 3:
                        System.out.print("Enter x: ");
                        double x3 = sc.nextDouble();
                        System.out.println("Result: ln(x) = " + df.format(Calculator.ln(x3)));
                        break;
                    case 4:
                        System.out.print("Enter x: ");
                        double base = sc.nextDouble();
                        System.out.print("Enter b: ");
                        long exp = sc.nextLong();
                        System.out.println("Result: pow(x,b) = " + df.format(Calculator.pow(base, exp)));
                        break;
                    case 5:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Please select 1-5.");
                }
            }
            catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}
