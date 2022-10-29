package com.wargame.wargame.controller;

import com.wargame.wargame.service.GameService;
import com.wargame.wargame.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    private ScoreService scoreService;

    @Autowired
    public ScoreController (ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/getscore")
    public String addPlayer(
            @RequestParam(name = "player1_name") String p1
    ) {
        return scoreService.getScore(p1);
    }
}
