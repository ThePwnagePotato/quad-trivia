package com.quad.trivia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionController controller;

    @Test
    void test() throws Exception {
        this.mockMvc.perform(get("/questions"))
                .andExpect(status().isOk());
    }
}
