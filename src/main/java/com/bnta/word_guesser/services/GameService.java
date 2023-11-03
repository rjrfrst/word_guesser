package com.bnta.word_guesser.services;
import com.bnta.word_guesser.models.Game;
import com.bnta.word_guesser.models.Guess;
import com.bnta.word_guesser.models.Reply;
import com.bnta.word_guesser.repositories.GameRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service //@Service extends BEAN
        //Therefore it takes other BEAN such as the RestController
public class GameService {

    @Autowired
    WordService wordService;

    @Autowired
    GameRepository gameRepository;

    //Properties
    private Game game;
    private String currentWord;
    private ArrayList<String> guessedLetters;

    //Default constructors
    public GameService() {
    }

    //Creating a new method
    public Reply startNewGame(){

//      Pick a random word from wordService
        String targetWord = wordService.getRandomWord();
        String currentWordStatus = Strings.repeat("_", targetWord.length()); // modify the currentWordStatus to reflect the random word's length

//      Assign the random word to the new game, which was the status added in
        Game game = new Game(targetWord, currentWordStatus);
        gameRepository.save(game); //
        //
        return new Reply(
                game.getCurrentState(),
                "Started new game",
                false
        );
    }

    //Needs to have an ID due to the controller
    public Reply processGuess(Guess guess, long id){
        // create new Reply object
        Reply reply;
        Optional<Game> optionalGame = getGameById(id);

        // Check if game has started
        //check if it is empty not null
        //This checks if it exist
        if (optionalGame.isEmpty()) {
            reply = new Reply(
                    null,
                    String.format("Game not found"),
                    false);
            return reply;
        }
        Game game = optionalGame.get();

        //check if game is finished, one of the checks
        if(game.isComplete()){
            return new Reply(
                    game.getCurrentState(),
                    "Game already complete",
                    false
            );
        }

        //keep the counter going up
        incrementGuesses(game);

        // check if letter has already been guessed
        if (game.getCurrentState().contains(guess.getLetter())) {
            reply = new Reply(
                    game.getCurrentState(),
                    String.format("Already found %s", guess.getLetter()),
                    false);
            return reply;
        }

        // check for incorrect guess
        if (!game.getWord().contains(guess.getLetter())) {
            gameRepository.save(game);
            return new Reply(
                    game.getCurrentState(),
                    String.format("%s is not in the word", guess.getLetter()),
                    false
            );
        }

        // process correct guess
        String runningResult = game.getWord();

        // build list of previously guessed letters
        List<String> guessedLetters = new ArrayList<>(Arrays.asList(
                game.getCurrentState().split(""))
        );

        // remove * characters
        guessedLetters.removeAll(Collections.singleton("\\*"));

        // add current guess
        guessedLetters.add(guess.getLetter());

        // update current state of game
        for (Character letter : game.getWord().toCharArray()) {
            if (!guessedLetters.contains(letter.toString())) {
                runningResult = runningResult.replace(letter, '*');
            }
        }

        game.setCurrentState(runningResult);

        // check if game won
        if (checkWinCondition(game)){
            game.setComplete(true);
            gameRepository.save(game);
            return new Reply(
                    game.getCurrentState(),
                    "Congratulations, you win!",
                    true
            );
        }

        gameRepository.save(game);

        return new Reply(
                game.getCurrentState(),
                String.format("%s is in the word", guess.getLetter()),
                true
        );

    }

    //Getters and Setters
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    public String getCurrentWord() {
        return currentWord;
    }
    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayList<String> getGuessedLetters() {
        return guessedLetters;
    }
    public void setGuessedLetters(ArrayList<String> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }

    //Method to have a single game
    //This will be the PathVariable created in the controller
    //An Optional datatype is a built-in java datatype defence to check if things exists or not.
    //The optional datatype, it forces Java to do error handling.
    public Optional<Game> getGameById(long id){
        return gameRepository.findById(id);
    }

    //method, it is a private method since it is an internal helper.
    private boolean checkWinCondition(Game game){
        String gameWord = game.getWord();
        String gameState = game.getCurrentState();
        return gameWord.equals(gameState);
    }

    //method, count how many guesses we have made in the game.
    private void incrementGuesses(Game game){
        game.setGuesses(game.getGuesses() + 1);
    }



} //Last curly bracket
