package com.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {

    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        generateDeck();
    }

    private void generateDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card newCard = new Card(rank, suit);
                cards.add(newCard);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (isEmpty()) {
            throw new IllegalStateException("No more cards in the deck!");
        }
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void reset() {
        cards.clear();
        generateDeck();
        shuffle();
    }
}
