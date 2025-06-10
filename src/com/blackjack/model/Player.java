package com.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private double balance;
    private List<Hand> hands;

    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.hands = new ArrayList<>();
    }

    public void addHand(Hand newHand) {
        hands.add(newHand);
    }

    public void addHand(int index, Hand newHand) {
        hands.add(index, newHand);
    }

    public List<Hand> getHands(){
        return hands;
    }

    public void resetHands() {
        hands.clear();
    }

    public double getBalance() {
        return this.balance;
    }

    public void adjustBalance(double amount) {
        this.balance += amount;
    }

    public void placeBet(Hand playerHand, double betAmount) {
        if (betAmount > balance) {
            throw new IllegalArgumentException("Insufficient funds to place bet.");
        }

        playerHand.setBet(betAmount);
        adjustBalance(-betAmount);
    }

    public String getName() {
        return name;
    }

    public void removeHand(Hand hand) {
        hands.remove(hand);
    }
}
