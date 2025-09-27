package com.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testCalculateSqrtValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "sqrt")
                        .param("x", "25"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", 5.0));
    }

    @Test
    void testCalculateSqrtInvalid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "sqrt")
                        .param("x", "-4"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    void testCalculateFactValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "fact")
                        .param("x", "5"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", 120.0));
    }

    @Test
    void testCalculateFactInvalid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "fact")
                        .param("x", "-2"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    void testCalculateLnValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "ln")
                        .param("x", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", Math.log(10.0)));
    }

    @Test
    void testCalculateLnInvalid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "ln")
                        .param("x", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    void testCalculatePowValid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "pow")
                        .param("x", "2")
                        .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", 8.0));
    }

    @Test
    void testCalculatePowInvalid() throws Exception {
        mockMvc.perform(post("/calculate")
                        .param("operation", "pow")
                        .param("x", "-2")
                        .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("error"));
    }
}
