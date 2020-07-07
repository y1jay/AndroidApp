package com.yijun.quiz;

public class QuizModel {
    private int Question;
    private boolean Answer;

    public QuizModel(int Question, boolean Answer) {
        this.Question = Question;
        this.Answer = Answer;
    }

    public int getmQuestion() {
        return Question;
    }

    public void setmQuestion(int Question) {
        this.Question = Question;
    }

    public boolean getAnswer() {
        return Answer;
    }

    public void setmAnswer(boolean Answer) {
        this.Answer = Answer;
    }
}
