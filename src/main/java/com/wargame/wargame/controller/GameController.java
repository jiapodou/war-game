package com.wargame.wargame.controller;

import com.wargame.wargame.model.Result;
import com.wargame.wargame.model.Score;
import com.wargame.wargame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController (GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping("/addplayer")
    public void addPlayer(
            @RequestParam(name = "player1_name") String p1,
            @RequestParam(name = "player2_name") String p2
    ) {
        gameService.add(p1,p2);
    }

    @PostMapping("/game")
    public Result playGame(
            @RequestParam(name = "player1_name") String p1,
            @RequestParam(name = "player2_name") String p2
    ) {
        return gameService.startGame(p1, p2);
    }

    @DeleteMapping("/removeplayer")
    public void deleteStay() {
        gameService.delete();
    }
}
