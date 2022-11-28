package com.wargame.wargame.War;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Game {

    private final Player player1 = new Player();
    private final Player player2 = new Player();
    private final Deck deck = new Deck();

    private int rounds;

    // initialize a set of game with the initial number of rounds played as 0
    public Game() {
        rounds = 0;
    }

    // play one round of game
    // return the winning player
    // if both players has nonempty hands after 1000 rounds of game
    // return the player with more cards in his/her hand as winner
    public Player startGame () {
        dealCardAtStart(player1);
        dealCardAtStart(player2);
        while (rounds < 1000) {
            List<Card> cardsOnTable = new ArrayList<>();
            PlayResult res = play(player1, player2, cardsOnTable);

            switch (res) {
                case WIN:
                    player1.getHand().addCards(cardsOnTable);
                    break;
                case LOSE:
                    // add every two cards in reverse order if player 2 win
                    for (int i = 0; i < cardsOnTable.size() / 2; i++) {
                        player2.getHand().addCard(cardsOnTable.get(i * 2 + 1));
                        player2.getHand().addCard(cardsOnTable.get(i * 2));
                    }
                    if (cardsOnTable.size() % 2 != 0) {
                        int idx = cardsOnTable.size();
                        player2.getHand().addCard(cardsOnTable.get(idx - 1));
                    }
                    break;
                case DRAW:
                    war(player1,player2,cardsOnTable);
                    break;
            }

            rounds++;

            if (player1.getHand().cardsLeft() == 0) return player2;
            if (player2.getHand().cardsLeft() == 0) return player1;
        }
        return player1.getHand().cardsLeft() > player2.getHand().cardsLeft() ? player1 : player2;
    }

    // return the number of rounds played
    public int getRounds() {return rounds;}

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    // deal all cards to both player at the beginning of each game
    public void dealCardAtStart (Player player) {
        for (int i = 0; i < 26; i++) {
            Card curCard = deck.dealCards();
            player.getHand().addCard(curCard);
        }
    }

    // return ENUM as result
    // WIN : player 1 wins/has larger rank than player 2
    // OR player 2 has no more cards

    // LOSE : player 1 loses/has smaller rank than over player 2
    // OR player 1 has no more cards

    // WIN : player 1 and player 2 has the same rank on their card
    private PlayResult play(Player player1, Player player2, List<Card> list) {

        Card curCardP1 = player1.getHand().removeCard();
        if (curCardP1 == null) return PlayResult.LOSE;
        list.add(curCardP1);

        Card curCardP2 = player2.getHand().removeCard();
        if(curCardP2 == null) return PlayResult.WIN;
        list.add(curCardP2);

        if (curCardP1.compareTo(curCardP2) < 0) {
            return PlayResult.WIN;
        } else if (curCardP1.compareTo(curCardP2) > 0) {
            return PlayResult.LOSE;
        } else {
            return PlayResult.DRAW;
        }
    }

    // recursively call war until not draw
    private void war (Player player1, Player player2, List<Card> warCards) {
        PlayResult res = warRound(player1, player2, warCards);
        switch (res) {
            case WIN:
                player1.getHand().addCards(warCards);
                break;
            case LOSE:
                player2.getHand().addCards(warCards);
                break;
            case DRAW:
                war(player1,player2,warCards);
                break;
        }
    }

    // return result enum indicate player1's result
    // WIN means player1 won/has larger rank than player 2
    // OR player 2 has no more cards

    // LOSE means player1 lost/has lower rank than player 2
    // OR player 1 has no more cards

    // DRAW means player1 and player 2 draw
    private PlayResult warRound(Player player1, Player player2, List<Card> warCards) {
        Card p1Third = null;
        Card p2Third = null;

        // player1 lost since not enough cards
        for (int i = 0; i < 2; i++) {
            Card curCardP1 = player1.getHand().removeCard();
            if (curCardP1 == null) return PlayResult.LOSE;
            warCards.add(curCardP1);
            p1Third = curCardP1;
        }

        // player1 won since opponent does not have enough cards
        for (int i = 0; i < 2; i++) {
            Card curCardP2 = player2.getHand().removeCard();
            if (curCardP2 == null) return PlayResult.WIN;
            warCards.add(curCardP2);
            p2Third = curCardP2;
        }

        if (p1Third.compareTo(p2Third) < 0) {
            return PlayResult.WIN;
        } else if (p1Third.compareTo(p2Third) > 0) {
            return PlayResult.LOSE;
        } else {
            return PlayResult.DRAW;
        }
    }

}
