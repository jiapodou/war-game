package com.wargame.wargame.model;

import javax.persistence.Entity;
import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private String winner;
    private int rounds;
    private int cardsLeftP1;
    private int cardsLeftP2;

    public Result() {
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getCardsLeftP1() {
        return cardsLeftP1;
    }

    public void setCardsLeftP1(int cardsLeftP1) {
        this.cardsLeftP1 = cardsLeftP1;
    }

    public int getCardsLeftP2() {
        return cardsLeftP2;
    }

    public void setCardsLeftP2(int cardsLeftP2) {
        this.cardsLeftP2 = cardsLeftP2;
    }
}
