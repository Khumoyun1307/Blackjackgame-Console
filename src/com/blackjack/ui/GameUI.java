package com.blackjack.ui;

import com.blackjack.model.Dealer;
import com.blackjack.model.Hand;
import com.blackjack.model.Move;
import com.blackjack.model.Player;
import com.blackjack.stats.GameStats;

/**
 * Interface for user interaction in the Blackjack game.
 */
public interface GameUI {
    /**
     * Displays the player's balance.
     * @param balance the balance
     */
    void displayBalance(double balance);

    /**
     * Displays a message without a newline.
     * @param message the message
     */
    void displayMessageWithoutLn(String message);

    /**
     * Displays a message.
     * @param message the message
     */
    void displayMessage(String message);

    /**
     * Prompts for deck choice.
     * @return the deck size
     */
    int promptDeckChoice();

    /**
     * Asks a yes/no question.
     * @param prompt the question
     * @return true for yes, false for no
     */
    boolean askYesNo(String prompt);

    /**
     * Prompts for a bet.
     * @param min the minimum bet
     * @param max the maximum bet
     * @return the bet amount
     */
    double getBet(double min, double max);

    /**
     * Prompts for a player move.
     * @param canDouble if double is allowed
     * @param canSplit if split is allowed
     * @param canSurrender if surrender is allowed
     * @return the move
     */
    Move getPlayerMove(boolean canDouble, boolean canSplit, boolean canSurrender);

    /**
     * Shows the player's hand(s).
     * @param player the player
     */
    void showPlayerHand(Player player);

    /**
     * Shows the dealer's hand.
     * @param dealer the dealer
     * @param hideHoleCard whether to hide the hole card
     */
    void showDealerHand(Dealer dealer, boolean hideHoleCard);

    /**
     * Shows the outcome of a round.
     * @param result the result
     */
    void showOutcome(String result);

    /**
     * Asks for an insurance bet.
     * @param maxInsurance the maximum insurance
     * @return the insurance bet
     */
    double askInsuranceBet(double maxInsurance);

    /**
     * Shows a hand.
     * @param hand the hand
     */
    void showHand(Hand hand);

    /**
     * Prompts for a menu choice.
     * @return the menu choice
     */
    int promptMenuChoice();

    /**
     * Shows the game statistics.
     * @param stats the stats
     */
    void showStats(GameStats stats);
}
