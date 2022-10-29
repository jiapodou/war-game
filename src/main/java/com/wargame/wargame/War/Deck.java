package com.wargame.wargame.War;

import java.util.*;

// data structure that stores Cards at the beginning of a game
public class Deck {

    private final List<Card> cards = new ArrayList<>();

    //create a new deck with shuffled 52 cards
    public Deck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                cards.add(new Card(r, s));
            }
        }
        shuffle();
    }

    //shuffle all cards in the deck
    private void shuffle() {
        Collections.shuffle(cards);
    }

    // remove one card from deck
    public Card dealCards() {
        return cards.remove(cards.size() - 1);
    }
}
