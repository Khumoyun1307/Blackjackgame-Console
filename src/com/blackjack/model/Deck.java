package com.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a standard 52-card deck.
 */
public class Deck {

    private List<Card> cards;

    /**
     * Constructs a new shuffled deck.
     */
    public Deck() {
        cards = new ArrayList<>();
        generateDeck();
        shuffle();
    }

    private void generateDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card newCard = new Card(rank, suit);
                cards.add(newCard);
            }
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the top of the deck.
     *
     * @return the drawn card
     */
    public Card drawCard() {
        if (isEmpty()) {
            throw new IllegalStateException("No more cards in the deck!");
        }
        return cards.remove(0);
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Gets the number of cards remaining.
     *
     * @return the size
     */
    public int getSize() {
        return cards.size();
    }

    /**
     * Gets the list of cards in the deck.
     *
     * @return the cards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Resets and shuffles the deck.
     */
    public void reset() {
        cards.clear();
        generateDeck();
        shuffle();
    }
}
