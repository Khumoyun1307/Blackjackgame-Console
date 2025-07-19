package com.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in Blackjack, holding hands and balance.
 */
public class Player {
    private String name;
    private double balance;
    private List<Hand> hands;

    /**
     * Constructs a player with a name and starting balance.
     * @param name the player's name
     * @param balance the starting balance
     */
    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.hands = new ArrayList<>();
    }

    /**
     * Adds a hand to the player.
     * @param newHand the hand to add
     */
    public void addHand(Hand newHand) {
        hands.add(newHand);
    }

    /**
     * Adds a hand at a specific index.
     * @param index the index to insert at
     * @param newHand the hand to add
     */
    public void addHand(int index, Hand newHand) {
        hands.add(index, newHand);
    }

    /**
     * Gets the list of hands.
     * @return the hands
     */
    public List<Hand> getHands(){
        return hands;
    }

    /**
     * Removes all hands.
     */
    public void resetHands() {
        hands.clear();
    }

    /**
     * Gets the player's balance.
     * @return the balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Adjusts the player's balance.
     * @param amount the amount to adjust by
     */
    public void adjustBalance(double amount) {
        this.balance += amount;
    }

    /**
     * Places a bet on a hand.
     * @param playerHand the hand to bet on
     * @param betAmount the bet amount
     */
    public void placeBet(Hand playerHand, double betAmount) {
        if (betAmount > balance) {
            throw new IllegalArgumentException("Insufficient funds to place bet.");
        }

        playerHand.setBet(betAmount);
        adjustBalance(-betAmount);
    }

    /**
     * Gets the player's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Removes a hand from the player.
     * @param hand the hand to remove
     */
    public void removeHand(Hand hand) {
        hands.remove(hand);
    }
}
