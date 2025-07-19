package com.blackjack.model;

/**
 * Represents the dealer in Blackjack.
 * Dealer always has infinite balance and one hand.
 */
public class Dealer extends Player{

    public Dealer() {
        super("Dealer", Double.POSITIVE_INFINITY); // House never runs out of money
        addHand(new Hand()); // // Start with one empty hand
    }

    /**
     * Gets the dealer's current hand.
     * @return the dealer's hand
     */
    public Hand getHand() {
        return getHands().get(0); // Dealer only has one hand
    }

    /**
     * Prepares the dealer for a new round.
     */
    public void newRound() {
        resetHands(); // clears hand list
        addHand(new Hand());
    }

    /**
     * Determines if the dealer should hit.
     * @return true if dealer should hit
     */
    public boolean shouldHit() {
        int value = getHand().getValue();
        return value < 17;
    }

    @Override
    public void adjustBalance(double amount) {
        // No-op for dealer, or log if needed
    }

    @Override
    public void placeBet(Hand playerHand, double bet){
        // Dealer does not place bet
    }
}
