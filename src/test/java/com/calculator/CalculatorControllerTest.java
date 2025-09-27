package com.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSqrtValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "sqrt")
                        .param("x", "25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    void testSqrtInvalid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "sqrt")
                        .param("x", "-4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void testFactValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "fact")
                        .param("x", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(120.0));
    }

    @Test
    void testFactInvalid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "fact")
                        .param("x", "-2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void testFactInvalidFraction() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "fact")
                        .param("x", "-2.1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void testLnValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "ln")
                        .param("x", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(Math.log(10.0)));
    }

    @Test
    void testLnInvalid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "ln")
                        .param("x", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void testPowValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "pow")
                        .param("x", "2")
                        .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0));
    }

    @Test
    void testPowNegativeExponent() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "pow")
                        .param("x", "2")
                        .param("b", "-2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(0.25));
    }

    @Test
    void testPowFractionalBaseNegativeExponentValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "pow")
                        .param("x", "2.5")
                        .param("b", "-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(0.4));
    }

    @Test
    void testPowFractionalBaseNegativeExponentInvalid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "pow")
                        .param("x", "-2")
                        .param("b", "0.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void testPowMissingExponent() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "pow")
                        .param("x", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void testUnknownOperation() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "unknown")
                        .param("x", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").exists());
    }
}
