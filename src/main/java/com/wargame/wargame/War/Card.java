package com.wargame.wargame.War;

public class Card implements Comparable<Card>{
    private final Rank rank;
    private final Suit suit;

     Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card another) {
        if (this.rank == another.rank) return 0;
        return this.rank.getFaceValue() > another.rank.getFaceValue() ? -1 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Card.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Card other = (Card) obj;
        return this.suit == other.suit && this.rank == other.rank;
    }
}
