package com.blackjack.stats;

import com.blackjack.stats.RoundSummary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    public List<RoundSummary> getHistory() {
        return new ArrayList<>(history);
    }

    public void clearHistory() {
        history.clear();
    }

    public int getTotalRounds() { return history.size(); }
    public double getAverageBet() { return history.isEmpty() ? 0 : totalBet / history.size(); }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public int getPushes() { return pushes; }
    public double getNetProfit() { return netProfit; }
    public double getWinRate() {
        int total = wins + losses + pushes;
        return total == 0 ? 0 : ((double) wins / total) * 100;
    }
}
