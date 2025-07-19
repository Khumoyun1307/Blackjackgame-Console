package com.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a shoe containing multiple decks of cards for Blackjack.
 */
public class Shoe {

    private final List<Card> cards;
    private final int totalDecks;;
    private final double reshuffleThreshhold = 0.25;

    /**
     * Constructs a shoe with the specified number of decks.
     * @param numberOfDecks the number of decks
     */
    public Shoe(int numberOfDecks) {
        this.totalDecks = numberOfDecks;
        this.cards = new ArrayList<>();
        initializeShoe();
    }

    private void initializeShoe() {
        cards.clear();
        for (int i = 0; i < totalDecks; i++) {
            Deck deck = new Deck();
            cards.addAll(deck.getCards());
        }
        shuffle();
    }

    /**
     * Shuffles the shoe.
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the shoe.
     * @return the drawn card
     */
    public Card drawCard() {
        if (isEmpty()) {
            throw new IllegalStateException("Shoe is out of cards! ");
        }
        return cards.remove(0);
    }

    /**
     * Checks if the shoe is empty.
     * @return true if empty
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Gets the number of cards left in the shoe.
     * @return the size
     */
    public int size() {
        return cards.size();
    }

    /**
     * Checks if the shoe needs reshuffling.
     * @return true if reshuffle is needed
     */
    public boolean needsReshuffle() {
        return size() <= (52 * totalDecks * reshuffleThreshhold);
    }

    /**
     * Reshuffles the shoe.
     */
    public void reshuffle(){
        initializeShoe();
    }

    /**
     * Gets the cards in the shoe.
     * @return the cards
     */
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    /**
     * Prepends a card to the top of the shoe (for testing).
     * @param card the card to prepend
     */
    public void prependCard(Card card) {
        cards.add(0, card); // Add to top of the deck
    }
}

