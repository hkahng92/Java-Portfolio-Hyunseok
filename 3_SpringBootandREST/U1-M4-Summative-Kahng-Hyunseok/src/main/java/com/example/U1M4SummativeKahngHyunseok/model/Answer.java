package com.example.U1M4SummativeKahngHyunseok.model;

import javax.validation.constraints.NotEmpty;

public class Answer {
   @NotEmpty(message = "You must enter a question to get an answer. Please try again.")
   private String question;
   private String answer;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
