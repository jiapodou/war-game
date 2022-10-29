package com.wargame.wargame.service;

import com.wargame.wargame.exception.UserNotExistException;
import com.wargame.wargame.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public String getScore(String playerName) {
        if (!scoreRepository.findById(playerName).isPresent()) {
            throw new UserNotExistException("User does not exist");
        }

        int score = scoreRepository.getReferenceById(playerName).getScore();
        return playerName + "'s score is " + score;
    }
}
