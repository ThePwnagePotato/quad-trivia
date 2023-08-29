package com.quad.trivia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class AnswerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void NoRequestBody() throws Exception {
        mockMvc.perform(post("/checkanswers"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void NoDatabase() throws Exception {
        Database.triviaList = null;
        Answer randomAnswer = new Answer("wrong", "answer");
        checkAnswerTest(randomAnswer, false);
    }

    @Test
    void WrongAnswer() throws Exception {
        // Add a trivia question to Database
        Database.triviaList = new ArrayList<>();
        Database.triviaList.add(new TriviaQuestion("Test", "multiple", "easy",
                "What is 1+1?", "2", Arrays.asList("0", "1", "3")));

        Answer wrongAnswer = new Answer("What is 1+1?", "3");
        checkAnswerTest(wrongAnswer, false);
    }
    @Test
    void CorrectAnswer() throws Exception {
        // Add a trivia question to Database
        Database.triviaList = new ArrayList<>();
        Database.triviaList.add(new TriviaQuestion("Test", "multiple", "easy",
                "What is 1+1?", "2", Arrays.asList("0", "1", "3")));

        Answer correctAnswer = new Answer("What is 1+1?", "2");
        checkAnswerTest(correctAnswer, true);
    }

    void checkAnswerTest(Answer answer, boolean expectedResult) throws Exception {
        MvcResult result = mockMvc.perform(
                        post("/checkanswers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(answer))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).isNotBlank();
        JsonNode jsonNode = mapper.readTree(content);
        assertThat(jsonNode.has("correct")).isTrue();
        assertThat(jsonNode.get("correct").isBoolean()).isTrue();
        assertThat(jsonNode.get("correct").asBoolean()).isEqualTo(expectedResult);
    }

}
