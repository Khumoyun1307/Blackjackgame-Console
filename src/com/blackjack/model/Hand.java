package com.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hand of cards in Blackjack.
 */
public class Hand {

    private final List<Card> cards;
    private double bet;
    private boolean isSplitHand = false;
    double insuranceBet;

    /**
     * Constructs a hand with two initial cards.
     * @param card1 the first card
     * @param card2 the second card
     */
    public Hand(Card card1, Card card2){
        cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
    }

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Adds a card to the hand.
     * @param card the card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Gets the value of the hand, accounting for Aces.
     * @return the hand value
     */
    public int getValue() {
        int total = 0;
        int aceCount = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.getRank() == Card.Rank.ACE) {
                aceCount++;
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;

    }

    /**
     * Gets a display string for the hand's value (e.g., "6/16" for soft hands).
     * @return the display value
     */
    public String getDisplayValue() {
        int total = 0;
        int aceCount = 0;
        int softAceUsed = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.getRank() == Card.Rank.ACE) {
                aceCount++;
            }
        }

        // Adjust for soft/hard Aces
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        softAceUsed = aceCount; // Remaining Aces still count as 11
        int softTotal = total;
        while (softAceUsed > 0 && total != 21){
            softTotal -= 10;
            softAceUsed -= 1;
        }
        if (softTotal != total)  {
            return softTotal + "/" + total;
        }else{
            return String.valueOf(total);
        }

    }

    /**
     * Checks if the hand is a blackjack.
     * @return true if blackjack
     */
    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    /**
     * Checks if the hand is bust.
     * @return true if bust
     */
    public boolean isBust() {
        return getValue() > 21;
    }

    /**
     * Sets the bet for this hand.
     * @param amount the bet amount
     */
    public void setBet(double amount) {
        this.bet = amount;
    }

    /**
     * Gets the bet for this hand.
     * @return the bet amount
     */
    public double getBet() {
        return bet;
    }

    /**
     * Sets whether this hand is a split hand.
     * @param isSplit true if split hand
     */
    public void setSplitHand(boolean isSplit) {
        this.isSplitHand = isSplit;
    }

    /**
     * Checks if this hand is a split hand.
     * @return true if split hand
     */
    public boolean isSplitHand() {
        return isSplitHand;
    }

    /**
     * Gets the cards in this hand.
     * @return the list of cards
     */
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    /**
     * Sets the insurance bet for this hand.
     * @param insuranceBet the insurance bet
     */
    public void setInsuranceBet(double insuranceBet) {
        this.insuranceBet = insuranceBet;
    }

    /**
     * Gets the insurance bet for this hand.
     * @return the insurance bet
     */
    public double getInsuranceBet() {
        return insuranceBet;
    }

    /**
     * Checks if the hand can be split.
     * @param playerBalance the player's balance
     * @return true if can split
     */
    public boolean canSplit(double playerBalance) {
        return cards.size() == 2 &&
                cards.get(0).getRank() == cards.get(1).getRank() &&
                playerBalance >= bet;
    }

    /**
     * Resets the hand to empty.
     */
    public void reset() {
        cards.clear();
        bet = 0;
        isSplitHand = false;
        insuranceBet = 0;
    }


}
