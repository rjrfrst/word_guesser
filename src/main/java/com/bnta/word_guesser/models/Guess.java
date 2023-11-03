package com.bnta.word_guesser.models;

//New DTO
public class Guess {

    //property
    private String letter;

    //Constructor
    public Guess(String inputLetter) {
        this.letter = inputLetter;
    }

    //Default constructor
    public Guess() {
    }

    //Getters and Setters
    public String getLetter() {
        return letter;
    }
    public void setLetter(String letter) {
        this.letter = letter;
    }


} //Last curly bracket
