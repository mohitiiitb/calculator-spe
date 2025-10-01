package com.calculator;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {

    @PostMapping("/calculate")
    public Map<String, Object> calculate(
            @RequestParam("operation") String operation,
            @RequestParam("x") double x,
            @RequestParam(value = "b", required = false) Double b) {

        Map<String, Object> resultMap = new HashMap<>();

        try {
            double result;

            switch (operation) {
                case "sqrt":
                    result = Calculator.sqrt(x);
                    break;
                case "fact":
                    result = Calculator.fact(x);
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

            resultMap.put("result", result);

        } catch (IllegalArgumentException | ArithmeticException e) {
            resultMap.put("error", e.getMessage());
        }

        return resultMap;
    }
}