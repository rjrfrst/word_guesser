package com.bnta.word_guesser.repositories;


import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Repository
public class WordList {

    //List type is an interface

    private List<String> words;


    //Constructor
    public WordList(){
        this.words = Arrays.asList(
                "hello",
                "goodbye",
                "mystery",
                "games",
                "spring",
                "controller",
                "repository");
    }

    //methods
    //pick a random word from the list above, to differentiate data access and service
    public String getRandomWord(){
        Random random = new Random();
        int randomIndex = random.nextInt(this.words.size());
        return this.words.get(randomIndex);
    }



} //Last curly bracket
