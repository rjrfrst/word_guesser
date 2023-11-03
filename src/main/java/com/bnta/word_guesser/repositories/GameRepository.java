package com.bnta.word_guesser.repositories;

import com.bnta.word_guesser.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    //First <>, insert the ID, which was in Game
    //Second<>, what is the ID type of the class you have set in Game. It can be 'Int, Long, etc.'

} //Last curly bracket
