package com.quad.trivia;

import java.util.*;

public record TriviaQuestion(String category,
                             String type,
                             String difficulty,
                             String question,
                             String correct_answer,
                             List<String> incorrect_answers) {
    // Adds all answers to an array in random order
    public List<String> scrambledAnswers() {
        List<String> answerList = new ArrayList<>(incorrect_answers);
        answerList.add(correct_answer);
        Collections.shuffle(answerList);
        return answerList;
    }

    public Question toQuestion() {
        return new Question(this.category, this.type, this.difficulty, this.question, scrambledAnswers());
    }
}
