package com.quad.trivia;

import java.util.Arrays;
import java.util.List;

public record Question(String category,
                       String type,
                       String difficulty,
                       String question,
                       List<String> answers) {
}

class TestQuestions {
    public static Question questionExample1() {
        return new Question("test",
                "multiple",
                "hard",
                "what is love",
                Arrays.asList(new String[]{"baby", "dont", "hurt"}));
    }

    public static Question questionExample2() {
        return new Question("test",
                "multiple",
                "easy",
                "1+1",
                Arrays.asList(new String[]{"1", "2", "3"}));
    }

    public static Question questionExample3() {
        return new Question("test",
                "boolean",
                "medium",
                "sky is red",
                Arrays.asList(new String[]{"true", "false"}));
    }

    public static Question questionExample4() {
        return new Question("test",
                "multiple",
                "hard",
                "tree is",
                Arrays.asList(new String[]{"big", "green", "small", "brown"}));
    }

    public static Question questionExample5() {
        return new Question("realtest",
                "multiple",
                "easy",
                "How many countries does Mexico border",
                Arrays.asList(new String[]{"1", "2", "3", "4"}));
    }
}