package com.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;
    private double bet;
    private boolean isSplitHand = false;
    double insuranceBet;

    public Hand(Card card1, Card card2){
        cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
    }

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

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

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public void setBet(double amount) {
        this.bet = amount;
    }

    public double getBet() {
        return bet;
    }

    public void setSplitHand(boolean isSplit) {
        this.isSplitHand = isSplit;
    }

    public boolean isSplitHand() {
        return isSplitHand;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void setInsuranceBet(double insuranceBet) {
        this.insuranceBet = insuranceBet;
    }

    public double getInsuranceBet() {
        return insuranceBet;
    }

    public boolean canSplit(double playerBalance) {
        return cards.size() == 2 &&
                cards.get(0).getRank() == cards.get(1).getRank() &&
                playerBalance >= bet;
    }

    public void reset() {
        cards.clear();
        bet = 0;
        isSplitHand = false;
        insuranceBet = 0;
    }


}
