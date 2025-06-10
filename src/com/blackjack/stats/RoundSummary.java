package com.blackjack.stats;

import java.time.LocalDateTime;

public class RoundSummary {
    private final String timestamp;
    private final int handNumber;
    private final double bet;
    private final double payout;
    private final String result; // Win, Lose, Push, Blackjack, Bust, Surrender, etc.
    private final int playerValue;
    private final int dealerValue;

    public RoundSummary(int handNumber, double bet, double payout, String result,
                        int playerValue, int dealerValue) {
        this.timestamp = LocalDateTime.now().toString();
        this.handNumber = handNumber;
        this.bet = bet;
        this.payout = payout;
        this.result = result;
        this.playerValue = playerValue;
        this.dealerValue = dealerValue;
    }

    public String toString() {
        return String.format("Hand %d | Bet: $%.2f | Result: %s | Payout: $%.2f | You: %d | Dealer: %d",
                handNumber, bet, result, payout, playerValue, dealerValue);
    }

    public int getDealerValue() {
        return dealerValue;
    }

    public double getBet() {
        return bet;
    }

    public String getResult() {
        return result;
    }

    public double getPayout() { return payout; }

    public int getHandNumber() {
        return handNumber;
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
