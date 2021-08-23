package ru.otus.spring.domain;

public class Answer {
    private final String answer;
    private final int id;
    private final boolean isRight;

    public Answer(int id, String answer, boolean isRight) {
        this.id = id;
        this.answer = answer;
        this.isRight = isRight;
    }

    @Override
    public String toString() {
        return id + ")" + answer + "\t\t";
    }
}
