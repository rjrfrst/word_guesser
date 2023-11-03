package com.bnta.word_guesser.models;

import jakarta.persistence.*;

//We only store the things we want in a database
//Any class in the database needs the @Entity
//Create a table

@Entity
@Table(name = "games")
public class Game {

    //properties

    //Create a unique identifier
    @Id //Makes this as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id; //Java side attribute. ID should be in the database. This needs to follow in th GameRepository.

    @Column(name = "word")
    private String word;

    @Column(name = "guesses")
    private int guesses;

    @Column(name = "complete")
    private boolean complete;

    @Column(name = "current_state")
    private String currentState;

    //Constructor
    //This constructor is what we use for the game application
    public Game(String inputWord, String inputCurrentState){
        this.word = inputWord;
        this.guesses = 0; //game will start at zero guesses
        this.complete = false; //game will be incomplete at the beginning
        this.currentState = inputCurrentState;
    }

    //Insert a default constructor, empty constructor that is used by Spring.
    //Default constructor
    public Game() {
    }

    //Getters and Setters for our properties.
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public int getGuesses() {
        return guesses;
    }
    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public boolean isComplete() {
        return complete;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCurrentState() {
        return currentState;
    }
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }


} //last curly bracket