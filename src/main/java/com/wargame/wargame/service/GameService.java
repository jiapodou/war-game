package com.wargame.wargame.service;

import com.wargame.wargame.War.Game;
import com.wargame.wargame.exception.UserNotExistException;
import com.wargame.wargame.model.Player;
import com.wargame.wargame.model.Result;
import com.wargame.wargame.model.Score;
import com.wargame.wargame.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
     private Game game;
     private ScoreRepository repository;

     @Autowired
     public GameService(ScoreRepository scoreRepository) {
          this.repository = scoreRepository;
     }

     // initialize score entry
     // requring player name
     // put score as 0 as default
     public void add(String player1Name, String player2Name) {
          Score P1 = new Score(player1Name, 0);
          Score P2 = new Score(player2Name, 0);
          repository.save(P1);
          repository.save(P2);
     }

     //start a game and return the result
     public Result startGame(String player1Name, String player2Name) {

          game = new Game();

          if (!repository.findById(player1Name).isPresent()) {
               throw new UserNotExistException("User does not exist");
          }
          if (!repository.findById(player2Name).isPresent()) {
               throw new UserNotExistException("User does not exist");
          }

          Score p1 = repository.getReferenceById(player1Name);
          int p1Score = p1.getScore();

          Score p2 = repository.getReferenceById(player2Name);
          int p2Score = p2.getScore();

          Result res = new Result();
          if (game.startGame() == game.getPlayer1()) {
               res.setWinner("winner is " + p1.getPlayer());
               p1.setScore(p1Score + 1);
          } else {
               res.setWinner("winner is " + p2.getPlayer());
               p2.setScore(p2Score + 1);
          }
          res.setRounds(game.getRounds());
          res.setCardsLeftP1(game.getPlayer1().getHand().cardsLeft());
          res.setCardsLeftP2(game.getPlayer2().getHand().cardsLeft());

          repository.save(p1);
          repository.save(p2);

          return res;
     }

     // delete all entries in the score DB
     public void delete() {
          repository.deleteAll();
     }

}
