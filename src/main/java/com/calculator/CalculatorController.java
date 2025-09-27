package com.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String index() {
        return "index"; // shows the form
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("operation") String operation,
            @RequestParam("x") double x,
            @RequestParam(value = "b", required = false) Long b,
            Model model) {

        try {
            double result;

            switch (operation) {
                case "sqrt":
                    result = Calculator.sqrt(x);
                    break;
                case "fact":
                    result = Calculator.fact((long) x);
                    break;
                case "ln":
                    result = Calculator.ln(x);
                    break;
                case "pow":
                    if (b == null) throw new IllegalArgumentException("Exponent b is required for pow.");
                    result = Calculator.pow(x, b);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operation.");
            }

            model.addAttribute("operation", operation);
            model.addAttribute("x", x);
            model.addAttribute("b", b);
            model.addAttribute("result", result);

        } catch (IllegalArgumentException | ArithmeticException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "result"; // shows result or error
    }
}
