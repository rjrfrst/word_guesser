package com.bnta.word_guesser.services;


import com.bnta.word_guesser.repositories.WordList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //BEAN are building blocks for Spring application that checks and loads the files in without manually importing
//BEAN are spring specific things
public class WordService {

    @Autowired //create an instance of the BEAN above
    WordList wordList; //Now we have a WordList Object


    //create a function
    public String getRandomWord(){
        return wordList.getRandomWord(); //This line will be modified later
    }













} // Last curly bracket