package com.bnta.word_guesser.controllers;

import com.bnta.word_guesser.models.Game;
import com.bnta.word_guesser.models.Guess;
import com.bnta.word_guesser.models.Reply;
import com.bnta.word_guesser.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController //We want Spring to understand that this is a controller. The listening ear
                //Indicates that this will be a BEAN
@RequestMapping (value = "/games") // Every route we define will have a default of /games. || localhost:8080/games
public class GameController {

    //Method that returns a new game
    //@GetMapping // GET localhost:8080/games, not needed to pass since we have the RequestMapping above
    //By using the GetMapping above, it was returning a method and creating which slips out the RESTful routes

    //Change Game to a Reply
    //Change Reply to ResponseEntity
    //In the ResponseEntity insert a Reply in the <_> which will need to match in the return.

    @Autowired // automatic
    GameService gameService;

    //First route
    @PostMapping // Everytime we post request, create a new game.
    public ResponseEntity<Reply> newGame(){
        Reply reply = gameService.startNewGame();
        return new ResponseEntity<>(reply, HttpStatus.CREATED);
    }

    //Second route
    @GetMapping(value = "/{id}")
    //id in the GetMapping and in the PathVariable must match
    public ResponseEntity<Reply> getGameStatus(@PathVariable long id){
        Reply reply;

        //This may exist, but maybe not.
        Optional<Game> game = gameService.getGameById(id);

        if (game.isEmpty()){
            reply = new Reply(
                    null, //no game state therefore null
                    "Game has not been started",
                    false
            );
        } else {
            reply = new Reply(
                    game.get().getCurrentState(),
                    "Game in progress",
                    false
            );
        }
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    //Third route, to update the game
    @PatchMapping(value ="/{id}")
    public ResponseEntity<Reply> handleGuess(@RequestBody Guess guess, @PathVariable long id){
        Reply reply = gameService.processGuess(guess, id);
        // return result
        return new ResponseEntity<>(reply, HttpStatus.OK);

    }

} //last curly bracket