package com.bnta.word_guesser.models;


//This is the DTO (Data Transfer Object) It is still a POJO class
public class Reply {

    //Properties
    private String wordState;
    private String message;
    private Boolean correct;

    //Constructor
    public Reply(String inputWordState, String inputMessage, Boolean inputCorrect){
        this.wordState = inputWordState;
        this.message = inputMessage;
        this.correct = inputCorrect;
    }

    //default constructor
    public Reply(){

    }

    //Getters and Setters
    public String getWordState() {
        return wordState;
    }
    public void setWordState(String wordState) {
        this.wordState = wordState;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
} //Last curly bracket
