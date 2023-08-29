package com.quad.trivia;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AnswerController {

    /**
     * Checks the answer to a given question.
     * Checks based on the question string. Accepts only JSON.
     *
     * @param answer  JSON string that should have values for "question" and "answer"
     * @return        JSON string with key "correct" and value whether the answer was correct.
     *                On correct JSON input but question not stored, returns false for value.
     *                Also returns false for value if database not initialized.
     */
    @CrossOrigin
    @PostMapping(
            value =  {"/checkanswers"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    public Map<String, Boolean> checkAnswers(@RequestBody Answer answer) {
        boolean correct = false;
        if (Database.triviaList == null) {
            return Collections.singletonMap("correct", false);
        }
        for (int i = 0; i < Database.triviaList.size(); i++) {
            TriviaQuestion tq = Database.triviaList.get(i);
            if (tq.question().equals(answer.question())) {
                correct = tq.correct_answer().equals(answer.answer());
                break;
            }
        }
        return Collections.singletonMap("correct", correct);
    }
}
