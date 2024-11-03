package com.lawrence123.quizapp;

import java.io.Serializable;

public class QuestionsAnswers implements Serializable {

    private String question;
    private String[] answers;
    private int correctAnswer;

    public QuestionsAnswers(String question, String[] answers, int correctAnswer){
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion(){
        return this.question;
    }

    public String[] getAnswers(){
        return this.answers;
    }

    public int getCorrectAnswer(){
        return this.correctAnswer;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswers (String[] answers){
        this.answers = answers;
    }

    public void setCorrectAnswer(int correctAnswer){
        this.correctAnswer = correctAnswer;
    }

}
