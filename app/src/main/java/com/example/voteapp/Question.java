package com.example.voteapp;

public class Question {
    private int id;
    private String question;
    private String option1;
    private String option2;

    // Constructor
    public Question(int id, String question, String option1, String option2) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    @Override
    public String toString() {
        return question; // This will display the question in ListView
    }
}
