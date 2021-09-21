package ru.otus.spring.domain;

public class Answer {
    private final String answer;
    private final boolean isRight;

    public Answer(String answer, boolean isRight) {
        this.answer = answer;
        this.isRight = isRight;
    }

    public boolean isRight() {
        return isRight;
    }

    public String getAnswer() {
        return answer;
    }
}
