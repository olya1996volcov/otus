package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private final String question;
    private List<Answer> answers;

    public Question(String question) {
        this.question = question;
        answers = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean getIsRightById(int id) {
        Answer answer = answers.get(id - 1);
        return answer.isRight();
    }


    @Override
    public String toString() {
        return question;
    }
}
