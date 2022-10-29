package com.wargame.wargame.War;

import java.util.*;


// data structure that stores Cards for player during the game
public class Hand {
    private final Queue<Card> hand;
    private int size;

    public Hand(){
        hand = new ArrayDeque<>();
    }

    public void addCard(Card current) {
        hand.offer(current);
        size++;
    }

    public void addCards(List<Card> current) {
        for (Card cur : current) {
            hand.offer(cur);
            size++;
        }
    }

    public Card removeCard() {
        if (hand.isEmpty()) return null;
        size--;
        return hand.poll();
    }

    public int cardsLeft() {return size;}
}
