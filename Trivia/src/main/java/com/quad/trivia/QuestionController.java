package com.quad.trivia;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionController {

    WebClient client = WebClient.create("https://opentdb.com/api.php?amount=5");

    /** Gets 5 random trivia questions from the open trivia database
     */
    @CrossOrigin
    @GetMapping("/questions")
    public List<Question> questions() throws Exception {
        TriviaAPIResponse tar = TriviaDeserializer.deserialize(getTriviaQuestions());
        if (tar.responseCode != 0) {
            throw new Exception("Did not receive questions correctly!");
        }
        List<TriviaQuestion> triviaList = new ArrayList<>(tar.results);
        Database.triviaList = triviaList;

        return getQuestions(triviaList);
    }

    // Performs the http request to retrieve questions
    private String getTriviaQuestions() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // Gets converts a list of TriviaQuestions to questions that do not show the answer
    private List<Question> getQuestions(List<TriviaQuestion> triviaList) {
        List<Question> qList = new ArrayList<>();
        triviaList.forEach(tq -> qList.add(tq.toQuestion()));
        return qList;
    }


}
