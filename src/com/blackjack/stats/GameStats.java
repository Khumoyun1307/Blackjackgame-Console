package com.blackjack.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tracks statistics for a Blackjack player session.
 */
public class GameStats implements Serializable {

    private int wins = 0;
    private int losses = 0;
    private int pushes = 0;
    private double totalBet = 0;
    private double netProfit = 0;

    private final List<RoundSummary> history;
    public GameStats(){
        this.history = new ArrayList<>();
    }
    /**
     * Records a round summary into the stats.
     * @param summary the round summary
     */
    public void recordRound(RoundSummary summary) {
        history.add(summary);
        totalBet += summary.getBet();
        netProfit += (summary.getPayout() - summary.getBet());

        switch (summary.getResult().toLowerCase()) {
            case "win" -> wins++;
            case "lose" -> losses++;
            case "push" -> pushes++;
        }
    }
    /**
     * Gets the history of round summaries.
     * @return the history list
     */
    public List<RoundSummary> getHistory() {
        return new ArrayList<>(history);
    }

    /**
     * Clears the round history.
     */
    public void clearHistory() {
        history.clear();
    }

    /**
     * Gets the total number of rounds played.
     * @return the total rounds
     */
    public int getTotalRounds() { return history.size(); }
    /**
     * Gets the average bet size.
     * @return the average bet
     */
    public double getAverageBet() { return history.isEmpty() ? 0 : totalBet / history.size(); }
    /**
     * Gets the number of wins.
     * @return the wins
     */
    public int getWins() { return wins; }
    /**
     * Gets the number of losses.
     * @return the losses
     */
    public int getLosses() { return losses; }
    /**
     * Gets the number of pushes.
     * @return the pushes
     */
    public int getPushes() { return pushes; }
    /**
     * Gets the net profit.
     * @return the net profit
     */
    public double getNetProfit() { return netProfit; }
    /**
     * Gets the win rate as a percentage.
     * @return the win rate
     */
    public double getWinRate() {
        int total = wins + losses + pushes;
        return total == 0 ? 0 : ((double) wins / total) * 100;
    }
}
